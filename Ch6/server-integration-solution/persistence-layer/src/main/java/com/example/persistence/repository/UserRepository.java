package com.example.persistence.repository;

import com.example.persistence.entity.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * The user repository.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsernameIgnoreCase(String username);
}
