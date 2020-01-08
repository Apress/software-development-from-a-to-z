package com.example.restapi.transformation;

import com.example.persistence.entity.user.User;
import com.example.restapi.dto.UserV1Dto;
import org.springframework.stereotype.Component;

/**
 * Transformation utility service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Component
public class TransformationsV1 {
    public UserV1Dto user2Dto(User user) {
        return UserV1Dto.builder()
                .withId(user.getId())
                .withUsername(user.getUsername())
                .withName(user.getName())
                .build();
    }
}
