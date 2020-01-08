package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * The user course version 1.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = UserCourseV1Dto.Builder.class)
public class UserCourseV1Dto implements Dto {
    private final Long id;
    private final Long courseId;
    private final Long startTime;
    private final Long finishTime;

    public UserCourseV1Dto(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.startTime = builder.startTime;
        this.finishTime = builder.finishTime;
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder implements FluentBuilder<UserCourseV1Dto> {
        private Long id;
        private Long courseId;
        private Long startTime;
        private Long finishTime;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withCourseId(Long courseId) {
            this.courseId = courseId;

            return this;
        }

        public Builder withStartTime(Long startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withFinishTime(Long finishTime) {
            this.finishTime = finishTime;
            return this;
        }

        @Override
        public UserCourseV1Dto build() {
            return new UserCourseV1Dto(this);
        }
    }

}
