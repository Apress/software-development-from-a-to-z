package com.example.service.questionanswer;

import com.example.commons.builder.JsonUtils;
import com.example.persistence.entity.questionanswer.QuestionAnswer;
import com.example.persistence.entity.usercourse.UserCourse;
import com.example.persistence.entity.usercourseanswer.UserCourseQuestionAnswer;
import com.example.persistence.repository.QuestionAnswerRepository;
import com.example.persistence.repository.UserCourseQaRepository;
import com.example.service.exception.NotFoundServiceException;
import com.example.service.usercourse.UserCourseService;
import com.google.common.base.Preconditions;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Default implementation of {@link QuestionAnswerService}.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Service
public class DefaultQuestionAnswerService implements QuestionAnswerService {
    @Autowired
    protected UserCourseService userCourseService;
    @Autowired
    protected QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    protected UserCourseQaRepository userCourseQaRepository;

    public QuestionAnswer get(Long id) {
        final Optional<QuestionAnswer> qaid = questionAnswerRepository.findById(id);
        if (!qaid.isPresent()) {
            throw new NotFoundServiceException("Question answer " + qaid + " doesn't exist.");
        }

        return qaid.get();
    }

    @Transactional
    @Override
    public UserCourseQuestionAnswer verifyAndRecord(Long userCourseId, Long questionAnswerId, Object answer) {
        Preconditions.checkNotNull(answer, "Answer cannot be null.");

        final UserCourse userCourse = userCourseService.get(userCourseId);
        final QuestionAnswer questionAnswer = get(questionAnswerId);

        final UserCourseQuestionAnswer userCourseQuestionAnswer = userCourseQaRepository.findByUserCourseAndQuestionAnswer(
                userCourse,
                questionAnswer
        ).orElseGet(() -> UserCourseQuestionAnswer.builder()
                .withUserCourse(userCourse)
                .withQuestionAnswer(questionAnswer)
                .build());

        userCourseQuestionAnswer.setCorrect(isAnswerCorrect(questionAnswer, answer));
        userCourseQuestionAnswer.setAnswer(JsonUtils.writeJson(answer));

        UserCourseQuestionAnswer save = userCourseQaRepository.save(userCourseQuestionAnswer);
        Hibernate.initialize(save.getQuestionAnswer());

        return save;
    }

    protected boolean isAnswerCorrect(QuestionAnswer solution, Object answer) {
        return JsonUtils.readJson(solution.getCorrectAnswer(), List.class)
                .equals(answer);
    }
}
