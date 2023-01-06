package com.adel.flightschedule.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class ErrorHandlingController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<String> handleError(){
        return new ResponseEntity<>("Not Found", HttpStatusCode.valueOf(200));
    }

}
