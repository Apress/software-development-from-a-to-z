package com.example.persistence.entity.user;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Maps to the user table.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Entity
@Table(name = "user")
public class User extends IdEntity {
    @Column(name = "username")
    protected String username;
    @Column(name = "password")
    protected String password;
    @Column(name = "salt")
    protected String salt;
    @Column(name = "name")
    protected String name;

    public User() {
        // NO-OP.
    }

    public User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.salt = builder.salt;
        this.name = builder.name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder implements FluentBuilder<User> {
        protected String username;
        protected String password;
        protected String salt;
        protected String name;

        public Builder withName(String name) {
            this.name = name;

            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;

            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;

            return this;
        }

        public Builder withSalt(String salt) {
            this.salt = salt;

            return this;
        }

        @Override
        public User build() {
            return new User(this);
        }
    }
}
