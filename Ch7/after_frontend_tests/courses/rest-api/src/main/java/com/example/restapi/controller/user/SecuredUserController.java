package com.example.restapi.controller.user;

import com.example.restapi.auth.UserDetailsImpl;
import com.example.restapi.dto.UserV1Dto;
import com.example.restapi.transformation.TransformationsV1;
import com.example.service.exception.ForbiddenServiceException;
import com.example.service.user.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * User controller.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@RestController
@RequestMapping("/api/v1/secured/users")
@ResponseBody
@Api(value = "secured users", description = "Secured endpoint for user specific operations.", authorizations = {@Authorization("oauth2")})
public class SecuredUserController {

    @Autowired
    protected TransformationsV1 transformationsV1;
    @Autowired
    protected UserService userService;

    @RequestMapping(value="/me", method = RequestMethod.GET)
    @ApiOperation(value = "Gets the user in the session.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user."),
            @ApiResponse(code = 404, message = "If the user doesn't exist."),
            @ApiResponse(code = 403, message = "If the user is not the owner.")
    })
    public UserV1Dto me(@AuthenticationPrincipal UserDetailsImpl user) {
        return transformationsV1.user2Dto(userService.get(user.getId()));
    }

    @RequestMapping(value= "/{id}", method = RequestMethod.PATCH)
    @ApiOperation(value = "Patches the user with the id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The patched user.")
    })
    public UserV1Dto patch(@AuthenticationPrincipal UserDetailsImpl user,
                           @ApiParam(value = "The user id.", required = true)
                           @PathVariable("id") Long id,
                           @ApiParam("The user object to patch.")
                           @RequestBody UserV1Dto userV1Dto) {
        if (!id.equals(user.getId())) {
            throw new ForbiddenServiceException("Not your user.");
        }

        return transformationsV1.user2Dto(userService.patch(id, userV1Dto.toUser()));
    }
}
