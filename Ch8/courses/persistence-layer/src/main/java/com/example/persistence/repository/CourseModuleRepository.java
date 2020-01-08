package com.example.persistence.repository;

import com.example.persistence.entity.coursemodule.CourseModule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * The course_module repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface CourseModuleRepository extends PagingAndSortingRepository<CourseModule, Long> {
    @Query("" +
            "SELECT cm " +
            "FROM CourseModule cm JOIN FETCH cm.module m " +
            "WHERE cm.courseId IN :courseIds ")
    List<CourseModule> findByCourseFetchModule(@Param("courseIds") Collection<Long> courseIds);
}
