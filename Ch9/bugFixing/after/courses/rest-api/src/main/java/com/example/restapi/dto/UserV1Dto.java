package com.example.restapi.dto;

import com.example.commons.builder.FluentBuilder;
import com.example.persistence.entity.user.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * Data Transfer Object for user information.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@JsonDeserialize(builder = UserV1Dto.Builder.class)
public class UserV1Dto implements Dto {

    private static final long serialVersionUID = -425508859743317205L;
    private final Long id;
    private final String password;
    private final String name;
    private final Integer age;
    private final String gender;
    private final String username;

    public UserV1Dto(Builder builder) {
        this.id = builder.id;
        this.password = builder.password;
        this.name = builder.name;
        this.age = builder.age;
        this.username = builder.username;
        this.gender = builder.gender;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public static Builder builder() {
        return new Builder();
    }

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);

        // Add others if needed.
        return user;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, age, gender, username, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final UserV1Dto other = (UserV1Dto) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.name, other.name)
                && Objects.equal(this.age, other.age)
                && Objects.equal(this.gender, other.gender)
                && Objects.equal(this.username, other.username)
                && Objects.equal(this.password, other.password)
                ;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("password", "[PROTECTED]")
                .add("name", name)
                .add("age", age)
                .add("gender", gender)
                .add("username", username)
                .toString();
    }

    @JsonPOJOBuilder
    public static class Builder implements FluentBuilder<UserV1Dto> {
        private Long id;
        private String name;
        private String password;
        private Integer age;
        private String gender;
        private String username;

        public Builder withPassword(String password) {
            this.password = password;

            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;

            return this;
        }

        public Builder withName(String name) {
            this.name = name;

            return this;
        }

        public Builder withId(Long id) {
            this.id = id;

            return this;
        }

        public Builder withAge(Integer age) {
            this.age = age;

            return this;
        }

        public Builder withGender(String gender) {
            this.gender = gender;

            return this;
        }

        @Override
        public UserV1Dto build() {
            return new UserV1Dto(this);
        }
    }

}
