package com.example.service.user;

import com.example.persistence.entity.user.User;
import com.example.service.exception.AlreadyExistsServiceException;
import com.example.service.exception.NotFoundServiceException;

/**
 * User related operations.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface UserService {
    /**
     * Gets the user with the id.
     *
     * @param id The id.
     * @return The user
     * @throws NotFoundServiceException If the user does not exist.
     */
    User get(Long id) throws NotFoundServiceException;

    /**
     * Creates the given user.
     *
     * @param user The user.
     * @return The created user.
     * @throws AlreadyExistsServiceException If the user already exists.
     */
    User create(User user) throws AlreadyExistsServiceException;

    /**
     * Patches the user with the id.
     *
     * @param id The user id.
     * @param user The new user data to replace.
     * @return The updated user.
     */
    User patch(Long id, User user);
}
