package com.example.persistence.repository;

import com.example.persistence.entity.questionanswer.QuestionAnswer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

/**
 * The course_module repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface QuestionAnswerRepository extends PagingAndSortingRepository<QuestionAnswer, Long> {
    List<QuestionAnswer> findByLectureId(Collection<Long> lectureIds);
}
