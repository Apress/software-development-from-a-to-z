package com.example.service.user;

import com.example.persistence.entity.user.User;
import com.example.persistence.repository.UserRepository;
import com.example.service.exception.AlreadyExistsServiceException;
import com.example.service.exception.NotFoundServiceException;
import com.example.service.exception.PreconditionFailedServiceException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Default implementation for the user service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Service
public class DefaultUserService implements UserService {
    protected static final Pattern EMAIL_REGEX =
            Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    protected static final Pattern PASSWORD_REGEX =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User get(Long id) throws NotFoundServiceException {
        Preconditions.checkNotNull(id, "User id cannot be null.");

        final User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new NotFoundServiceException("User not found.");
        }

        return user;
    }

    @Transactional
    @Override
    public User create(User user) throws AlreadyExistsServiceException {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    @Transactional
    public User patch(Long id, User user) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    protected void validateUserCreation(User user) {
        Preconditions.checkNotNull(user, "User cannot be null.");
        Preconditions.checkNotNull(user.getUsername(), "Username cannot be null.");
        Preconditions.checkNotNull(user.getPassword(), "Password cannot be null.");

        if (!EMAIL_REGEX.matcher(user.getUsername()).matches()) {
            throw new PreconditionFailedServiceException("Username is not valid.");
        }
        if (!PASSWORD_REGEX.matcher(user.getPassword()).matches()) {
            throw new PreconditionFailedServiceException("Password is not valid.");
        }
    }
}
