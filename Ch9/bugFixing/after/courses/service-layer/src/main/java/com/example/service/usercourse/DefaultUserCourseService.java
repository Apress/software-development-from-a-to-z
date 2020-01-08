package com.example.service.usercourse;

import com.example.persistence.entity.course.Course;
import com.example.persistence.entity.user.User;
import com.example.persistence.entity.usercourse.UserCourse;
import com.example.persistence.repository.UserCourseRepository;
import com.example.service.course.CourseService;
import com.example.service.exception.AlreadyExistsServiceException;
import com.example.service.exception.NotFoundServiceException;
import com.example.service.user.UserService;
import com.example.service.usercourse.model.UserCourseConfigModel;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * Default implementation of the user course service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Service
public class DefaultUserCourseService implements UserCourseService {

    @Autowired
    protected UserCourseRepository userCourseRepository;
    @Autowired
    protected CourseService courseService;
    @Autowired
    protected UserService userService;

    @Override
    public Set<UserCourse> activeCourses(Long userId, Collection<Long> activeCourses) {
        return userCourseRepository.findByUserIdAndFinishTimeIsNullAndCourseIdIn(activeCourses, userId);
    }

    @Transactional
    @Override
    public UserCourse create(Long userId, UserCourseConfigModel config) {
        final User user = userService.get(userId);
        final Course course = courseService.get(config.getCourseId());
        validateCreation(userId, config);

        return userCourseRepository.save(UserCourse.builder()
                .withCourse(course)
                .withUser(user)
                .withStartTime(new Date())
                .build());
    }

    @Override
    public UserCourse get(Long userCourseId) {
        Preconditions.checkNotNull(userCourseId, "User course id.");

        Optional<UserCourse> ucid = userCourseRepository.findById(userCourseId);

        if (!ucid.isPresent()) {
            throw new NotFoundServiceException("User course " + " doesn't exist.");
        }

        return ucid.get();
    }

    public void validateCreation(Long userId, UserCourseConfigModel config) {
        if (!activeCourses(userId, ImmutableList.of(config.getCourseId())).isEmpty()) {
            throw new AlreadyExistsServiceException("Course is already running for the user.");
        }
    }
}
