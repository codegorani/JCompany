package com.spring.jcompany.springboot.web.front;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class FrontTestController {

    @PostMapping("/ip")
    public ResponseEntity<?> sendIp(HttpServletRequest request) {
        return new ResponseEntity<>(request.getRemoteAddr(), HttpStatus.OK);
    }
}
