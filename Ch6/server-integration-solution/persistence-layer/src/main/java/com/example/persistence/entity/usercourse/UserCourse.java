package com.example.persistence.entity.usercourse;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;
import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Maps to the user_course table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "user_course")
public class UserCourse extends IdEntity {
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Basic(optional = false)
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "finish_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishTime;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;
    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    public UserCourse() {
        // NO-OP.
    }

    public UserCourse(Builder builder) {
        this.user = builder.user;
        this.course = builder.course;
        this.startTime = builder.startTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public static class Builder implements FluentBuilder<UserCourse> {
        private Course course;
        private User user;
        private Date startTime;

        public Builder withCourse(Course course) {
            this.course = course;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withStartTime(Date startTime) {
            this.startTime = startTime;
            return this;
        }

        @Override
        public UserCourse build() {
            return new UserCourse(this);
        }
    }
}
