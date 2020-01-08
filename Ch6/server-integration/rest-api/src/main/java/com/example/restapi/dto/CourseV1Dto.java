package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * Models the courses v1.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = CourseV1Dto.Builder.class)
public class CourseV1Dto implements Dto {
    private final Long id;
    private final String name;
    private final String description;
    private final Integer durationHours;
    private final Boolean active;

    public CourseV1Dto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.durationHours = builder.durationHours;
        this.active = builder.active;
    }

    public Boolean getActive() {
        return active;
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

    public Integer getDurationHours() {
        return durationHours;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder implements FluentBuilder<CourseV1Dto> {
        private Long id;
        private String name;
        private String description;
        private Integer durationHours;
        private Boolean active;

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

        public Builder withDurationHours(Integer durationHours) {
            this.durationHours = durationHours;
            return this;
        }

        public Builder withActive(Boolean active) {
            this.active = active;

            return this;
        }

        @Override
        public CourseV1Dto build() {
            return new CourseV1Dto(this);
        }
    }
}
