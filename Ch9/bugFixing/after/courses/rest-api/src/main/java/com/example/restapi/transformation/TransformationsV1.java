package com.example.restapi.transformation;

import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.coursemodule.CourseModule;
import com.example.persistence.entity.modulelecture.ModuleLecture;
import com.example.persistence.entity.questionanswer.QuestionAnswer;
import com.example.persistence.entity.user.User;
import com.example.persistence.entity.usercourse.UserCourse;
import com.example.persistence.entity.usercourseanswer.UserCourseQuestionAnswer;
import com.example.persistence.repository.CourseModuleRepository;
import com.example.persistence.repository.ModuleLectureRepository;
import com.example.persistence.repository.QuestionAnswerRepository;
import com.example.persistence.repository.UserCourseQaRepository;
import com.example.restapi.dto.*;
import com.example.service.usercourse.UserCourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Transformation utility service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Component
public class TransformationsV1 {
    protected ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected UserCourseService userCourseService;
    @Autowired
    protected CourseModuleRepository courseModuleRepository;
    @Autowired
    protected ModuleLectureRepository moduleLectureRepository;
    @Autowired
    protected QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    protected UserCourseQaRepository userCourseQaRepository;

    public UserV1Dto user2Dto(User user) {
        return UserV1Dto.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .withName(user.getName())
                .build();
    }

    public Page<CourseV1Dto> courses2Dto(Rendering rendering, Page<Course> courses) {
        if (courses.getContent().isEmpty()) {
            return Page.empty(courses.getPageable());
        }

        final List<Long> courseIds = courses.map(Course::getId).getContent();


        final Map<Long, UserCourse> activeCourses = (rendering.getUser() != null
                ? userCourseService.activeCourses(rendering.getUser().getId(), courseIds)
                : ImmutableSet.<UserCourse>of()).stream().collect(Collectors.toMap(UserCourse::getCourseId, x -> x));
        final Multimap<Long, CourseModule> modules = rendering.getInclude().contains("modules")
                ? Multimaps.index(courseModuleRepository.findByCourseFetchModule(courseIds), CourseModule::getCourseId)
                : ImmutableMultimap.of();

        return courses.map(x -> CourseV1Dto.builder()
                .withId(x.getId())
                .withName(x.getName())
                .withDescription(x.getDescription())
                .withDurationHours(x.getDurationHours())
                .withActive(activeCourses.containsKey(x.getId()))
                .withModules(courseModules2Dto(
                        rendering.newBuilder().withUserCourseId(Optional.ofNullable(activeCourses.get(x.getId())).map(UserCourse::getId).orElse(null)).build(),
                        modules.get(x.getId())))
                .build());
    }

    private List<CourseModuleV1Dto> courseModules2Dto(Rendering rendering, Collection<CourseModule> courseModules) {
        if (courseModules.isEmpty()) {
            return ImmutableList.of();
        }

        final List<Long> moduleIds = courseModules.stream().map(CourseModule::getModuleId)
                .collect(Collectors.toList());
        ImmutableListMultimap<Long, ModuleLecture> lectures =
                Multimaps.index(moduleLectureRepository.findByModuleIdsFetchLecture(moduleIds), ModuleLecture::getModuleId);


        return courseModules.stream()
                .map(x -> CourseModuleV1Dto.builder()
                        .withId(x.getId())
                        .withCourseId(x.getCourseId())
                        .withModuleId(x.getModuleId())
                        .withStartTime(Optional.ofNullable(x.getStartTime()).map(Date::getTime).orElse(null))
                        .withFinishTime(Optional.ofNullable(x.getFinishTime()).map(Date::getTime).orElse(null))
                        .withDescription(x.getModule().getDescription())
                        .withDurationMinutes(x.getModule().getDurationMinutes())
                        .withName(x.getModule().getName())
                        .withNumber(x.getNumber())
                        .withLectures(moduleLectures2Dto(rendering, lectures.get(x.getModuleId())))
                        .build()
                ).collect(Collectors.toList());
    }

    private List<ModuleLectureV1Dto> moduleLectures2Dto(Rendering rendering, ImmutableList<ModuleLecture> moduleLectures) {
        if (moduleLectures.isEmpty()) {
            return ImmutableList.of();
        }

        final List<Long> lectureIds =
                moduleLectures.stream().map(ModuleLecture::getLectureId).collect(Collectors.toList());
        final ImmutableListMultimap<Long, QuestionAnswer> qaa =
                Multimaps.index(questionAnswerRepository.findByLectureId(lectureIds), QuestionAnswer::getLectureId);

        return moduleLectures.stream()
                .map(x -> ModuleLectureV1Dto.builder()
                        .withId(x.getId())
                        .withLectureId(x.getLectureId())
                        .withModuleId(x.getModuleId())
                        .withNumber(x.getNumber())
                        .withName(x.getLecture().getName())
                        .withDescription(x.getLecture().getDescription())
                        .withMediaSource(x.getLecture().getMediaSource())
                        .withQuestionAnswers(questionAnswers2Dto(rendering, qaa.get(x.getLectureId())))
                        .build()
                ).collect(Collectors.toList());
    }

    public List<QuestionAnswerV1Dto> questionAnswers2Dto(Rendering rendering, ImmutableList<QuestionAnswer> questionAnswers) {
        if (questionAnswers.isEmpty()) {
            return ImmutableList.of();
        }


        final Map<Long, UserCourseQuestionAnswer> ucqa = (rendering.getUserCourseId() != null
                ? userCourseQaRepository.findByQuestionAnswerIdInAndUserCourseId(
                questionAnswers.stream().map(QuestionAnswer::getId).collect(Collectors.toList()),
                rendering.getUserCourseId())
                : ImmutableList.<UserCourseQuestionAnswer>of())
                .stream().collect(Collectors.toMap(UserCourseQuestionAnswer::getQuestionAnswerId, x -> x));

        return questionAnswers.stream()
                .map(x -> QuestionAnswerV1Dto.builder()
                        .withId(x.getId())
                        .withQuestion(x.getQuestion())
                        .withAnswer(readJson(x.getAnswer(), List.class))
                        .withLectureId(x.getLectureId())
                        .withCorrect(Optional.ofNullable(ucqa.get(x.getId())).map(UserCourseQuestionAnswer::getCorrect).orElse(null))
                        .build()
                ).collect(Collectors.toList());
    }

    public CourseV1Dto course2Dto(Rendering rendering, Course course) {
        return courses2Dto(rendering, new PageImpl<>(ImmutableList.of(course), PageRequest.of(0, 1), 1))
                .getContent().get(0);
    }

    public UserCourseV1Dto userCourse2Dto(Rendering rendering, UserCourse userCourse) {
        return userCourses2Dto(rendering, new PageImpl<>(ImmutableList.of(userCourse), PageRequest.of(0, 1), 1))
                .getContent().get(0);
    }

    public Page<UserCourseV1Dto> userCourses2Dto(Rendering rendering, Page<UserCourse> userCourses) {
        return userCourses.map(x -> UserCourseV1Dto.builder()
                .withId(x.getId())
                .withCourseId(x.getCourseId())
                .withStartTime(Optional.ofNullable(x.getStartTime()).map(Date::getTime).orElse(null))
                .withFinishTime(Optional.ofNullable(x.getFinishTime()).map(Date::getTime).orElse(null))
                .build());

    }

    protected Object readJson(String json, Class<?> klass) {
        try {
            return objectMapper.readValue(json, klass);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
