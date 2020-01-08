package com.example.persistence.repository;

import com.example.persistence.entity.usercourse.UserCourse;
import com.google.common.collect.ImmutableSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;

/**
 * The user_course repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface UserCourseRepository extends PagingAndSortingRepository<UserCourse, Long> {

    @Query("" +
            "SELECT uc.courseId " +
            "FROM UserCourse uc " +
            "WHERE uc.courseId IN :courseIds " +
            "AND uc.userId = :userId " +
            "AND uc.finishTime IS NULL ")
    Set<Long> _findByUserIdAndFinishTimeIsNullAndCourseIdIn(
            @Param("courseIds")
            Collection<Long> courseIds,
            @Param("userId")
            Long userId);

    default Set<Long> findByUserIdAndFinishTimeIsNullAndCourseIdIn(
            @Param("courseIds")
            Collection<Long> courseIds,
            @Param("userId")
            Long userId) {
        if (CollectionUtils.isEmpty(courseIds)) {
            return ImmutableSet.of();
        }

        return _findByUserIdAndFinishTimeIsNullAndCourseIdIn(courseIds, userId);
    }
}
