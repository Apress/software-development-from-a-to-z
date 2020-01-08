package com.example.persistence.entity.course;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Maps to the course table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "course")
public class Course extends IdEntity {
    @Column(name = "name")
    protected String name;
    @Column(name = "description")
    protected String description;
    @Column(name = "duration_hours")
    protected Integer durationHours;

    public Course() {
        // NO-OP.
    }

    public Course(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.durationHours = builder.durationHours;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<Course> {
        protected String name;
        protected String description;
        protected Integer durationHours;

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

        @Override
        public Course build() {
            return new Course(this);
        }
    }
}
