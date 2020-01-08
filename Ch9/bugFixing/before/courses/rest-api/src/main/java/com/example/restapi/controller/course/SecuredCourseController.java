package com.example.restapi.controller.course;

import com.example.restapi.auth.UserDetailsImpl;
import com.example.restapi.dto.CourseV1Dto;
import com.example.restapi.transformation.Rendering;
import com.example.restapi.transformation.TransformationsV1;
import com.example.service.course.CourseService;
import com.google.common.base.Splitter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping("/api/v1/secured/courses")
@ResponseBody
public class SecuredCourseController {

    @Autowired
    protected TransformationsV1 transformationsV1;
    @Autowired
    protected CourseService courseService;

    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    @ApiOperation("Gets the course with the id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The paginated list of available courses.")
    })
    public CourseV1Dto get(@AuthenticationPrincipal UserDetailsImpl user,
                           @ApiParam("The course id.")
                           @PathVariable("id") Long id,
                           @ApiParam("The list of what to include.")
                           @RequestParam(value = "include", required = false, defaultValue = "") String include) {
        final Rendering rendering = Rendering.builder()
                .withUser(user)
                .withInclude(Splitter.on(",").trimResults().omitEmptyStrings().splitToList(include))
                .build();

        return transformationsV1.course2Dto(rendering, courseService.get(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("Lists the available courses.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The paginated list of available courses.")
    })
    public Page<CourseV1Dto> list(@AuthenticationPrincipal UserDetailsImpl user,
                                  Pageable pageable) {
        final Rendering rendering = Rendering.builder()
                .withUser(user)
                .build();

        return transformationsV1.courses2Dto(rendering, courseService.list(pageable));
    }
}
