package com.example.persistence.entity.modulelecture;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;
import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.lecture.Lecture;
import com.example.persistence.entity.module.Module;

import javax.persistence.*;
import java.util.Date;

/**
 * Maps to the module_lecture table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "module_lecture")
public class ModuleLecture extends IdEntity {
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;
    @Column(name = "number")
    private Integer number;

    @Column(name = "lecture_id", insertable = false, updatable = false)
    private Long lectureId;
    @Column(name = "module_id", insertable = false, updatable = false)
    private Long moduleId;

    public ModuleLecture() {
        // NO-OP.
    }

    public ModuleLecture(Builder builder) {
        this.module = builder.module;
        this.lecture = builder.lecture;
        this.number = builder.number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<ModuleLecture> {
        private Lecture lecture;
        private Module module;
        private Integer number;

        public Builder withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public void setLecture(Lecture lecture) {
            this.lecture = lecture;
        }

        public void setModule(Module module) {
            this.module = module;
        }

        @Override
        public ModuleLecture build() {
            return new ModuleLecture(this);
        }
    }
}
