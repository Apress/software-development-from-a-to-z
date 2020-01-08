package com.example.service.usercourse;

import com.example.persistence.entity.usercourse.UserCourse;
import com.example.service.usercourse.model.UserCourseConfigModel;

import java.util.Collection;
import java.util.Set;

/**
 * User course related operations.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface UserCourseService {
    Set<Long> activeCourses(Long userId, Collection<Long> activeCourses);
    UserCourse create(Long userId, UserCourseConfigModel config);
}
