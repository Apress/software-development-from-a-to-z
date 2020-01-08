package com.example.service.course;

import com.example.persistence.entity.course.Course;
import com.example.persistence.repository.CourseRepository;
import com.example.service.exception.NotFoundServiceException;
import com.example.service.exception.PreconditionFailedServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Default implementation of the course service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Service
public class DefaultCourseService implements CourseService {
    @Autowired
    protected CourseRepository courseRepository;

    @Override
    public Course get(Long id) throws NotFoundServiceException {
        if (id == null) {
            throw new PreconditionFailedServiceException("Course id cannot be null.");
        }

        final Optional<Course> course = courseRepository.findById(id);

        if (!course.isPresent()) {
            throw new NotFoundServiceException("Course " + id + " doesn't exist.");
        }

        return course.get();
    }

    @Override
    public Page<Course> list(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }
}
