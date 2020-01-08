package com.example.restapi.controller.usercourse;

import com.example.persistence.repository.UserCourseRepository;
import com.example.restapi.auth.UserDetailsImpl;
import com.example.restapi.dto.QuestionAnswerV1Dto;
import com.example.restapi.dto.UserCourseConfigV1Dto;
import com.example.restapi.dto.UserCourseV1Dto;
import com.example.restapi.transformation.Rendering;
import com.example.restapi.transformation.TransformationsV1;
import com.example.service.exception.ForbiddenServiceException;
import com.example.service.questionanswer.QuestionAnswerService;
import com.example.service.usercourse.UserCourseService;
import com.google.common.collect.ImmutableList;
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
    @Autowired
    protected UserCourseRepository userCourseRepository;
    @Autowired
    protected QuestionAnswerService questionAnswerService;

    @RequestMapping(method = RequestMethod.POST)
    public UserCourseV1Dto create(@AuthenticationPrincipal UserDetailsImpl user,
                                  @RequestBody UserCourseConfigV1Dto config) {
        final Rendering rendering = Rendering.builder().withUser(user).build();
        return transformationsV1
                .userCourse2Dto(rendering, userCourseService.create(user.getId(), config.toModel()));
    }

    @RequestMapping(value = "/{ucid}/questionanswer/{qaid}", method = RequestMethod.POST)
    public QuestionAnswerV1Dto createOrUpdate(@AuthenticationPrincipal UserDetailsImpl user,
                                              @RequestBody QuestionAnswerV1Dto dto,
                                              @PathVariable("ucid") Long ucid,
                                              @PathVariable("qaid") Long qaid) {

        if (!userCourseService.get(ucid).getUserId().equals(user.getId())) {
            throw new ForbiddenServiceException("Not user resource.");
        }
        final Rendering rendering = Rendering.builder()
                .withUser(user)
                .withUserCourseId(ucid)
                .build();

        return transformationsV1.questionAnswers2Dto(
                rendering,
                ImmutableList.of(questionAnswerService.verifyAndRecord(ucid, qaid, dto.getAnswer()).getQuestionAnswer()))
                .get(0);
    }
}
