package com.example.restapi.controller.course;

import com.example.restapi.auth.UserDetailsImpl;
import com.example.restapi.dto.CourseV1Dto;
import com.example.restapi.transformation.TransformationsV1;
import com.example.service.course.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Courses controller.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@RestController
@RequestMapping("/api/v1/public/courses")
@ResponseBody
public class PublicCourseController {

    @Autowired
    protected TransformationsV1 transformationsV1;
    @Autowired
    protected CourseService courseService;

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public CourseV1Dto get(@AuthenticationPrincipal UserDetailsImpl user,
                           @PathVariable("id") Long id) {
        return transformationsV1.course2Dto(user, courseService.get(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("Lists the available courses.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The paginated list of available courses.")
    })
    public Page<CourseV1Dto> list(@AuthenticationPrincipal UserDetailsImpl user,
                                  Pageable pageable) {
        return transformationsV1.courses2Dto(user, courseService.list(pageable));
    }
}
