package com.example.service.usercourse.model;

import com.example.commons.builder.FluentBuilder;

/**
 * Configuration when starting or handling courses.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public class UserCourseConfigModel {
    private final Long courseId;

    public UserCourseConfigModel(Builder builder) {
        this.courseId = builder.courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<UserCourseConfigModel> {
        private Long courseId;

        public Builder withCourseId(Long courseId) {
            this.courseId = courseId;

            return this;
        }

        @Override
        public UserCourseConfigModel build() {
            return new UserCourseConfigModel(this);
        }
    }
}
