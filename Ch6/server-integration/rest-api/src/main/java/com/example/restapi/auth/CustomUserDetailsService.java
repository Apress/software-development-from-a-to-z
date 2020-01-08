package com.example.restapi.auth;

import com.example.persistence.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom user details service for refreshes.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserDetailsImpl.builder()
                .withId(userRepository.findByUsernameIgnoreCase(username).getId())
                .withUsername(username)
                .withAuthorities(ImmutableList.of(new SimpleGrantedAuthority("STUDENT")))
                .build()
                ;
    }
}
