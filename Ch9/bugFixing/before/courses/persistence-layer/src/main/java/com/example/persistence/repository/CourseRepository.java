package com.example.persistence.repository;

import com.example.persistence.entity.course.Course;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The course repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
