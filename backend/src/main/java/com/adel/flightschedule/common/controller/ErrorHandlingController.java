package com.adel.flightschedule.common.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ErrorHandlingController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(final HttpServletRequest request){
        return "forward:/";
    }



}
