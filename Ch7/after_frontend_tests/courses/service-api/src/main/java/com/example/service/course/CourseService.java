package com.example.service.course;

import com.example.persistence.entity.course.Course;
import com.example.service.exception.NotFoundServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Course related operations.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface CourseService {
    /**
     * Gets the course with the id.
     *
     * @param id The id.
     * @return The course.
     * @throws NotFoundServiceException If the course does not exist.
     */
    Course get(Long id) throws NotFoundServiceException;

    /**
     * Lists the courses.
     *
     * @param pageable The pageable information.
     * @return The page of courses.
     */
    Page<Course> list(Pageable pageable);
}
