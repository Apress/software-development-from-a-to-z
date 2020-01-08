package com.example.persistence.entity.coursemodule;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;
import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.module.Module;
import com.example.persistence.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Maps to the course_module table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "course_module")
public class CourseModule extends IdEntity {
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
    @Basic(optional = false)
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "finish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;
    @Column(name = "number")
    private Integer number;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;
    @Column(name = "module_id", insertable = false, updatable = false)
    private Long moduleId;

    public CourseModule() {
        // NO-OP.
    }

    public CourseModule(Builder builder) {
        this.module = builder.module;
        this.course = builder.course;
        this.startTime = builder.startTime;
        this.finishTime = builder.finishTime;
        this.number = builder.number;
    }

    public Integer getNumber() {
        return number;
    }

    public void withNumber(Integer number) {
        this.number = number;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Course getCourse() {
        return course;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long userId) {
        this.moduleId = moduleId;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Module getModule() {
        return module;
    }

    public void setModuler(Module module) {
        this.module = module;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<CourseModule> {
        private Course course;
        private Module module;
        private Date startTime;
        private Date finishTime;
        private Integer number;

        public Builder withCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder withFinishTime(Date finishTime) {
            this.finishTime = finishTime;
            return this;
        }

        public Builder withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public Builder withModule(Module module) {
            this.module = module;
            return this;
        }

        public Builder withStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public CourseModule build() {
            return new CourseModule(this);
        }
    }
}
