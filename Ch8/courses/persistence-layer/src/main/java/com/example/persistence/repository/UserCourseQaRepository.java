package com.example.persistence.repository;

import com.example.persistence.entity.questionanswer.QuestionAnswer;
import com.example.persistence.entity.usercourse.UserCourse;
import com.example.persistence.entity.usercourseanswer.UserCourseQuestionAnswer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * The user_course_qa repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface UserCourseQaRepository extends PagingAndSortingRepository<UserCourseQuestionAnswer, Long> {
    List<UserCourseQuestionAnswer> findByQuestionAnswerIdInAndUserCourseId(
            Collection<Long> questionAnswerIds,
            Long courseId
    );

    Optional<UserCourseQuestionAnswer> findByUserCourseAndQuestionAnswer(UserCourse userCourse, QuestionAnswer questionAnswer);
}
