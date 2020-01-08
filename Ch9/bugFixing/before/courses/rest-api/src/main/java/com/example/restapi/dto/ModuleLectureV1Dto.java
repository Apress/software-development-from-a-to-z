package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

/**
 * Lecture dto.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = ModuleLectureV1Dto.Builder.class)
public class ModuleLectureV1Dto implements Dto {
    protected final Long id;
    protected final Long moduleId;
    protected final Long lectureId;
    protected final String name;
    protected final String description;
    protected final Integer durationMinutes;
    protected final String mediaSource;
    protected final Integer number;
    protected final List<QuestionAnswerV1Dto> questionAnswers;

    public ModuleLectureV1Dto(Builder builder) {
        this.id = builder.id;
        this.moduleId = builder.moduleId;
        this.lectureId = builder.lectureId;
        this.name = builder.name;
        this.description = builder.description;
        this.durationMinutes = builder.durationMinutes;
        this.mediaSource = builder.mediaSource;
        this.questionAnswers = builder.questionAnswers;
        this.number = builder.number;
    }

    public Integer getNumber() {
        return number;
    }

    public List<QuestionAnswerV1Dto> getQuestionAnswers() {
        return questionAnswers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public String getMediaSource() {
        return mediaSource;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder implements FluentBuilder<ModuleLectureV1Dto> {
        protected Long id;
        protected Long moduleId;
        protected Long lectureId;
        protected String name;
        protected String description;
        protected Integer durationMinutes;
        protected String mediaSource;
        protected List<QuestionAnswerV1Dto> questionAnswers;
        protected Integer number;

        public Builder withModuleId(Long moduleId) {
            this.moduleId = moduleId;
            return this;
        }

        public Builder withLectureId(Long lectureId) {
            this.lectureId = lectureId;
            return this;
        }

        public Builder withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder withQuestionAnswers(List<QuestionAnswerV1Dto> questionAnswers) {
            this.questionAnswers = questionAnswers;
            return this;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withDurationMinutes(Integer durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        public Builder withMediaSource(String mediaSource) {
            this.mediaSource = mediaSource;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;

            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;

            return this;
        }

        @Override
        public ModuleLectureV1Dto build() {
            return new ModuleLectureV1Dto(this);
        }
    }
}
