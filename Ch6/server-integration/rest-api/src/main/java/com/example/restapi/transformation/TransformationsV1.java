package com.example.restapi.transformation;

import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.user.User;
import com.example.persistence.entity.usercourse.UserCourse;
import com.example.restapi.auth.UserDetailsImpl;
import com.example.restapi.dto.CourseV1Dto;
import com.example.restapi.dto.UserCourseV1Dto;
import com.example.restapi.dto.UserV1Dto;
import com.example.service.usercourse.UserCourseService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Transformation utility service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Component
public class TransformationsV1 {
    @Autowired
    protected UserCourseService userCourseService;

    public UserV1Dto user2Dto(User user) {
        return UserV1Dto.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .withName(user.getName())
                .build();
    }

    public Page<CourseV1Dto> courses2Dto(UserDetailsImpl user, Page<Course> courses) {
        final Set<Long> activeCourses;
        if (user != null) {
            activeCourses = userCourseService.activeCourses(user.getId(),
                    courses.map(Course::getId).getContent());
        } else {
            activeCourses = ImmutableSet.of();
        }

        return courses.map(x -> CourseV1Dto.builder()
                .withId(x.getId())
                .withName(x.getName())
                .withDescription(x.getDescription())
                .withDurationHours(x.getDurationHours())
                .withActive(activeCourses.contains(x.getId()))
                .build());
    }

    public CourseV1Dto course2Dto(UserDetailsImpl user, Course course) {
        return courses2Dto(user, new PageImpl<>(ImmutableList.of(course), PageRequest.of(0, 1), 1))
                .getContent().get(0);
    }

    public UserCourseV1Dto userCourse2Dto(UserDetailsImpl user, UserCourse userCourse) {
        return userCourses2Dto(user, new PageImpl<>(ImmutableList.of(userCourse), PageRequest.of(0, 1), 1))
                .getContent().get(0);
    }

    public Page<UserCourseV1Dto> userCourses2Dto(UserDetailsImpl user, Page<UserCourse> userCourses) {
        return userCourses.map(x -> UserCourseV1Dto.builder()
                .withId(x.getId())
                .withCourseId(x.getCourseId())
                .withStartTime(Optional.ofNullable(x.getStartTime()).map(Date::getTime).orElse(null))
                .withFinishTime(Optional.ofNullable(x.getFinishTime()).map(Date::getTime).orElse(null))
                .build());

    }
}
