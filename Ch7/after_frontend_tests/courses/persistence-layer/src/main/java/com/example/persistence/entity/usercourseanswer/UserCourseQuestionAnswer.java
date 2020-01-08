package com.example.persistence.entity.usercourseanswer;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;
import com.example.persistence.entity.questionanswer.QuestionAnswer;
import com.example.persistence.entity.usercourse.UserCourse;

import javax.persistence.*;
import java.util.Optional;

/**
 * Maps to the user_course_qa table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "user_course_qa")
public class UserCourseQuestionAnswer extends IdEntity {
    @ManyToOne
    @JoinColumn(name = "user_course_id")
    private UserCourse userCourse;
    @ManyToOne
    @JoinColumn(name = "qa_id")
    private QuestionAnswer questionAnswer;

    @Column(name = "answer")
    private String answer;
    @Column(name = "correct")
    private Boolean correct;

    @Column(name = "user_course_id", insertable = false, updatable = false)
    private Long userCourseId;
    @Column(name = "qa_id", insertable = false, updatable = false)
    private Long questionAnswerId;

    public UserCourseQuestionAnswer() {
        // NO-OP.
    }

    public UserCourseQuestionAnswer(Builder builder) {
        this.userCourse = builder.userCourse;
        this.questionAnswer = builder.questionAnswer;
        this.answer = builder.answer;
        this.correct = builder.correct;
    }

    public Long getUserCourseId() {
        return Optional.ofNullable(userCourseId)
                .orElseGet(() -> Optional.ofNullable(userCourse).map(UserCourse::getId).orElse(null));
    }

    public Long getQuestionAnswerId() {
        return Optional.ofNullable(questionAnswerId)
                .orElseGet(() -> Optional.ofNullable(questionAnswer).map(QuestionAnswer::getId).orElse(null));
    }

    public UserCourse getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(UserCourse userCourse) {
        this.userCourse = userCourse;
    }

    public QuestionAnswer getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(QuestionAnswer questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<UserCourseQuestionAnswer> {
        private UserCourse userCourse;
        private QuestionAnswer questionAnswer;
        private String answer;
        private Boolean correct;

        public Builder withUserCourse(UserCourse userCourse) {
            this.userCourse = userCourse;
            return this;
        }

        public Builder withQuestionAnswer(QuestionAnswer questionAnswer) {
            this.questionAnswer = questionAnswer;
            return this;
        }

        public Builder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        public Builder withCorrect(Boolean correct) {
            this.correct = correct;
            return this;
        }

        @Override
        public UserCourseQuestionAnswer build() {
            return new UserCourseQuestionAnswer(this);
        }
    }
}
