package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Question answer dto
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = QuestionAnswerV1Dto.Builder.class)
public class QuestionAnswerV1Dto implements Dto {
    private final Long id;
    private final String question;
    private final Object answer;
    private final Long lectureId;
    private final Boolean correct;

    public QuestionAnswerV1Dto(Builder builder) {
        this.id = builder.id;
        this.lectureId = builder.lectureId;
        this.question = builder.question;
        this.answer = builder.answer;
        this.correct = builder.correct;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public Long getId() {
        return id;
    }

    public Object getQuestion() {
        return question;
    }

    public Object getAnswer() {
        return answer;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder implements FluentBuilder<QuestionAnswerV1Dto> {
        private Long id;
        private Long lectureId;
        private String question;
        private Object answer;
        private Boolean correct;

        public Builder withCorrect(Boolean correct) {
            this.correct = correct;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withLectureId(Long lectureId) {
            this.lectureId = lectureId;
            return this;
        }

        public Builder withQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder withAnswer(Object answer) {
            this.answer = answer;
            return this;
        }

        @Override
        public QuestionAnswerV1Dto build() {
            return new QuestionAnswerV1Dto(this);
        }
    }
}
