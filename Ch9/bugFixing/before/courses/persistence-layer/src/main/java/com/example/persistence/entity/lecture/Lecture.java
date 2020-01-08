package com.example.persistence.entity.lecture;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Maps to the lecture table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "lecture")
public class Lecture extends IdEntity {
    @Column(name = "name")
    protected String name;
    @Column(name = "description")
    protected String description;
    @Column(name = "duration_minutes")
    protected Integer durationMinutes;
    @Column(name = "media_source")
    protected String mediaSource;

    public Lecture() {
        // NO-OP.
    }

    public Lecture(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.durationMinutes = builder.durationMinutes;
        this.mediaSource = builder.mediaSource;

    }

    public String getMediaSource() {
        return mediaSource;
    }

    public void setMediaSource(String mediaSource) {
        this.mediaSource = mediaSource;
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

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<Lecture> {
        protected String name;
        protected String description;
        protected Integer durationMinutes;
        protected String mediaSource;

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
        public Lecture build() {
            return new Lecture(this);
        }
    }
}
