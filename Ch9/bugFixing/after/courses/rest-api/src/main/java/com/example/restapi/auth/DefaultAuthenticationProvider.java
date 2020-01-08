package com.example.restapi.auth;

import com.example.persistence.entity.user.User;
import com.example.persistence.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Default implementation of {@link AbstractUserDetailsAuthenticationProvider}.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public class DefaultAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        // NO-OP.
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        final User user = userRepository.findByUsernameIgnoreCase(username);
        if (user == null) {
            // For security reasons usually this is not sent, in general a credentials do not match is sent
            // so an attacker wouldn't guess if a user in fact exists or not.
            throw new UsernameNotFoundException("User '" + username + "' does not exist.");
        }

        if (!passwordEncoder.matches(authentication.getCredentials().toString() + user.getSalt(), user.getPassword())) {
            throw new BadCredentialsException("Password doesn't match.");
        }

        return UserDetailsImpl.builder()
                .withId(user.getId())
                .withUsername(username)
                .withAuthorities(ImmutableList.of(new SimpleGrantedAuthority("STUDENT")))
                .build()
                ;
    }
}
