package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

/**
 * Dto for module.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = CourseModuleV1Dto.Builder.class)
public class CourseModuleV1Dto implements Dto {
    protected final Long id;
    protected final Long courseId;
    protected final Long moduleId;
    protected final String name;
    protected final String description;
    protected final Integer durationMinutes;
    protected final Integer number;
    protected final Long startTime;
    protected final Long finishTime;
    protected final List<ModuleLectureV1Dto> lectures;

    public CourseModuleV1Dto(Builder builder) {
        this.id = builder.id;
        this.courseId = builder.courseId;
        this.moduleId = builder.moduleId;
        this.name = builder.name;
        this.description = builder.description;
        this.durationMinutes = builder.durationMinutes;
        this.number = builder.number;
        this.startTime = builder.startTime;
        this.finishTime = builder.finishTime;
        this.lectures = builder.lectures;

    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public List<ModuleLectureV1Dto> getLectures() {
        return lectures;
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

    public Integer getNumber() {
        return number;
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
    public static class Builder implements FluentBuilder<CourseModuleV1Dto> {
        protected Long id;
        protected Long courseId;
        protected Long moduleId;
        protected String name;
        protected String description;
        protected Integer durationMinutes;
        protected Integer number;
        protected Long startTime;
        protected Long finishTime;
        protected List<ModuleLectureV1Dto> lectures;

        public Builder withCourseId(Long courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder withModuleId(Long moduleId) {
            this.moduleId = moduleId;
            return this;
        }

        public Builder withLectures(List<ModuleLectureV1Dto> lectures) {
            this.lectures = lectures;
            return this;
        }

        public Builder withNumber(Integer number) {
            this.number = number;
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

        public Builder withId(Long id) {
            this.id = id;
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

        public Builder withDurationMinutes(Integer durationMinutes) {
            this.durationMinutes = durationMinutes;
            return this;
        }

        @Override
        public CourseModuleV1Dto build() {
            return new CourseModuleV1Dto(this);
        }
    }
}
