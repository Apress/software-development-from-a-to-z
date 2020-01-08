package com.example.restapi.controller.user;

import com.example.restapi.dto.UserV1Dto;
import com.example.restapi.transformation.TransformationsV1;
import com.example.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * User controller.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@RestController
@RequestMapping("/api/v1/public/users")
@ResponseBody
public class PublicUserController {
    @Autowired
    protected TransformationsV1 transformationsV1;
    @Autowired
    protected UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserV1Dto create(@RequestBody UserV1Dto userV1Dto) {
        return transformationsV1.user2Dto(userService.create(userV1Dto.toUser()));
    }
}
