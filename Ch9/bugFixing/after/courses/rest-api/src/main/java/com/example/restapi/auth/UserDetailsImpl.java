package com.example.restapi.auth;

import com.example.commons.builder.FluentBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Holds the user information.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = UserDetailsImpl.Builder.class)
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = -350813636561323092L;
    private Collection<? extends GrantedAuthority> authorities;
    private Long id;
    private String password;
    private String username;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private UserDetailsImpl(Builder builder) {
        this.id = builder.id;
        this.authorities = builder.authorities;
        this.password = builder.password;
        this.username = builder.username;
        this.accountNonExpired = builder.accountNonExpired;
        this.accountNonLocked = builder.accountNonLocked;
        this.credentialsNonExpired = builder.credentialsNonExpired;
        this.enabled = builder.enabled;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, authorities, password, username, accountNonExpired,
                accountNonLocked, credentialsNonExpired, enabled);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserDetailsImpl other = (UserDetailsImpl) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.authorities, other.authorities)
                && Objects.equal(this.password, other.password)
                && Objects.equal(this.username, other.username)
                && Objects.equal(this.accountNonExpired, other.accountNonExpired)
                && Objects.equal(this.accountNonLocked, other.accountNonLocked)
                && Objects.equal(this.credentialsNonExpired, other.credentialsNonExpired)
                && Objects.equal(this.enabled, other.enabled)
                ;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("authorities", authorities)
                .add("username", username)
                .add("password", "[PROTECTED]")
                .add("accountNonExpired", accountNonExpired)
                .add("accountNonLocked", accountNonLocked)
                .add("credentialsNonExpired", credentialsNonExpired)
                .add("enabled", enabled)
                .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder implements FluentBuilder<UserDetailsImpl> {
        private Collection<GrantedAuthority> authorities = Sets.newHashSet();
        private String password;
        private String username;
        private boolean accountNonExpired = true;
        private boolean accountNonLocked = true;
        private boolean credentialsNonExpired = true;
        private boolean enabled = true;
        private Long id;

        public Builder() {
            // NO-OP.
        }

        public Builder withId(Long id) {
            this.id = id;

            return this;
        }

        @JsonProperty("authorities")
        public Builder withRawAuthorities(Collection<String> authorities) {
            if (authorities != null) {
                this.authorities = authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            }

            return this;
        }

        @JsonIgnore
        public Builder withAuthorities(Collection<GrantedAuthority> authorities) {
            this.authorities = authorities;

            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;

            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;

            return this;
        }

        public Builder withAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;

            return this;
        }

        public Builder withAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;

            return this;
        }

        public Builder withCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;

            return this;
        }

        public Builder withEnabled(boolean enabled) {
            this.enabled = enabled;

            return this;
        }

        @Override
        public UserDetailsImpl build() {
            return new UserDetailsImpl(this);
        }
    }
}
