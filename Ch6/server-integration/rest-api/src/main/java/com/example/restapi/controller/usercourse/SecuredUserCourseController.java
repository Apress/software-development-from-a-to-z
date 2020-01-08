package com.example.restapi.controller.usercourse;

import com.example.restapi.auth.UserDetailsImpl;
import com.example.restapi.dto.UserCourseConfigV1Dto;
import com.example.restapi.dto.UserCourseV1Dto;
import com.example.restapi.transformation.TransformationsV1;
import com.example.service.usercourse.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * User Courses controller.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@RestController
@RequestMapping("/api/v1/secured/usercourses")
@ResponseBody
public class SecuredUserCourseController {

    @Autowired
    protected TransformationsV1 transformationsV1;
    @Autowired
    protected UserCourseService userCourseService;

    @RequestMapping(method = RequestMethod.POST)
    public UserCourseV1Dto create(@AuthenticationPrincipal UserDetailsImpl user,
                                  @RequestBody UserCourseConfigV1Dto config) {
        return transformationsV1.userCourse2Dto(user, userCourseService.create(user.getId(), config.toModel()));
    }
}
