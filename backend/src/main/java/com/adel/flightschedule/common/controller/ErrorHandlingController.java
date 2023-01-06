package com.adel.flightschedule.common.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ErrorHandlingController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(final HttpServletRequest request){
        final Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(null!=status){
            final Integer statusCode = Integer.valueOf(status.toString());
            if(statusCode.equals(HttpStatus.NOT_FOUND.value())){
                return new ResponseEntity<>("Not Found", HttpStatusCode.valueOf(statusCode));
            }else if(statusCode.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())){
                return new ResponseEntity<>("Internal error", HttpStatusCode.valueOf(statusCode));
            }
        }

        return new ResponseEntity<>("Unknown", HttpStatusCode.valueOf(404));
    }

}
