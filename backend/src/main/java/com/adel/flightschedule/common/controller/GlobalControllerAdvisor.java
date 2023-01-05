package com.adel.flightschedule.common.controller;

import com.adel.flightschedule.common.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvisor {

    @ExceptionHandler(value = {
            Exception.class
    })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGlobalException(Exception ex, WebRequest request) {
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        log.info(errorResponse.toString());
        return errorResponse;
    }

}
