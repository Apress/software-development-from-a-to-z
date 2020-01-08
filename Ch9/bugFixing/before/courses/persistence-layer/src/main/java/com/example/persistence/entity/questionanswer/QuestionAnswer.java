package com.example.persistence.entity.questionanswer;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;
import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.lecture.Lecture;
import com.example.persistence.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Maps to the question_answer table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "question_answer")
public class QuestionAnswer extends IdEntity {
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @Column(name = "question")
    private String question;
    @Column(name = "answer")
    private String answer;
    @Column(name = "correct_answer")
    private String correctAnswer;

    @Column(name = "lecture_id", insertable = false, updatable = false)
    private Long lectureId;

    public QuestionAnswer() {
        // NO-OP.
    }

    public QuestionAnswer(Builder builder) {
        this.lecture = builder.lecture;
        this.question = builder.question;
        this.answer = builder.answer;
        this.correctAnswer = builder.correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void withLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getQuestion() {
        return question;
    }

    public void withQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void withAnswer(String answer) {
        this.answer = answer;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void withLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<QuestionAnswer> {
        private Lecture lecture;
        private String question;
        private String answer;
        private String correctAnswer;

        public Builder withCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder withLecture(Lecture lecture) {
            this.lecture = lecture;
            return this;
        }

        public Builder withQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder withAnswer(String answer) {
            this.answer = answer;
            return this;
        }

        @Override
        public QuestionAnswer build() {
            return new QuestionAnswer(this);
        }
    }
}
