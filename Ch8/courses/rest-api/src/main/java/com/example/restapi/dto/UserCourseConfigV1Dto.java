package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.example.service.usercourse.model.UserCourseConfigModel;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Configuration when starting or handling courses.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = UserCourseConfigV1Dto.Builder.class)
public class UserCourseConfigV1Dto implements Dto {
    private final Long courseId;

    public UserCourseConfigV1Dto(Builder builder) {
        this.courseId = builder.courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public UserCourseConfigModel toModel() {
        return UserCourseConfigModel.builder()
                .withCourseId(courseId)
                .build();
    }

    @JsonPOJOBuilder
    public static class Builder implements FluentBuilder<UserCourseConfigV1Dto> {
        private Long courseId;

        public Builder withCourseId(Long courseId) {
            this.courseId = courseId;

            return this;
        }

        @Override
        public UserCourseConfigV1Dto build() {
            return new UserCourseConfigV1Dto(this);
        }
    }
}
