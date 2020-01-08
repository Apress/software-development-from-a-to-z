package com.example.restapi.controller;

import com.example.service.exception.AlreadyExistsServiceException;
import com.example.service.exception.ForbiddenServiceException;
import com.example.service.exception.NotFoundServiceException;
import com.example.service.exception.PreconditionFailedServiceException;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Default exception handler.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(AlreadyExistsServiceException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public Map<String, ?> conflict(AlreadyExistsServiceException e) {
        return ImmutableMap.of("reason", "That resource already exists: " + e.getMessage());
    }

    @ExceptionHandler(PreconditionFailedServiceException.class)
    @ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
    public Map<String, ?> preconditionFailed(PreconditionFailedServiceException e) {
        return ImmutableMap.of("reason", "Precondition failed: " + e.getMessage());
    }

    @ExceptionHandler(ForbiddenServiceException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public Map<String, ?> forbidden(ForbiddenServiceException e) {
        return ImmutableMap.of("reason", "Forbidden: " + e.getMessage());
    }

    @ExceptionHandler(NotFoundServiceException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, ?> notFound(NotFoundServiceException e) {
        return ImmutableMap.of("reason", "Not found: " + e.getMessage());
    }
}
