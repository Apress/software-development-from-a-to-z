package com.example.persistence.repository;

import com.example.persistence.entity.lecture.Lecture;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The lecture repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface LectureRepository extends PagingAndSortingRepository<Lecture, Long> {

}
