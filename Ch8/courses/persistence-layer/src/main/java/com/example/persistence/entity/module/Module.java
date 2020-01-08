package com.example.persistence.entity.module;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Maps to the module table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "module")
public class Module extends IdEntity {
    @Column(name = "name")
    protected String name;
    @Column(name = "description")
    protected String description;
    @Column(name = "duration_minutes")
    protected Integer durationMinutes;

    public Module() {
        // NO-OP.
    }

    public Module(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.durationMinutes = builder.durationMinutes;

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

    public static class Builder implements FluentBuilder<Module> {
        protected String name;
        protected String description;
        protected Integer durationMinutes;

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
        public Module build() {
            return new Module(this);
        }
    }
}
