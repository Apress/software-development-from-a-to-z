package com.example.service.questionanswer;

import com.example.persistence.entity.usercourseanswer.UserCourseQuestionAnswer;

/**
 * Service to deal with question and answers.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface QuestionAnswerService {

    UserCourseQuestionAnswer verifyAndRecord(Long userCourseId, Long questionAnswerId, Object answer);
}
