package com.example.restapi.transformation;

import com.example.commons.builder.FluentBuilder;
import com.example.restapi.auth.UserDetailsImpl;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Rendering configuration.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public class Rendering {
    private final List<String> include;
    private final UserDetailsImpl user;
    private final Long userCourseId;

    public Rendering(Builder builder) {
        this.include = builder.include;
        this.user = builder.user;
        this.userCourseId = builder.userCourseId;
    }

    public Long getUserCourseId() {
        return userCourseId;
    }

    public UserDetailsImpl getUser() {
        return user;
    }

    public boolean userIsSet() {
        return user != null;
    }

    public List<String> getInclude() {
        return include;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder implements FluentBuilder<Rendering> {
        private List<String> include = Lists.newArrayList();
        private UserDetailsImpl user;
        private Long userCourseId;

        public Builder() {
            // NO-OP.
        }

        public Builder(Rendering rendering) {
            this.include = rendering.include;
            this.user = rendering.user;
            this.userCourseId = rendering.userCourseId;
        }

        public Builder withUserCourseId(Long userCourseId) {
            this.userCourseId = userCourseId;
            return this;
        }

        public Builder withInclude(List<String> include) {
            this.include = include;
            return this;
        }

        public Builder withUser(UserDetailsImpl user) {
            this.user = user;

            return this;
        }

        @Override
        public Rendering build() {
            return new Rendering(this);
        }
    }
}
