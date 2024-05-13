package com.tpe.controller;

import com.tpe.dto.LoginRquest;
import com.tpe.dto.RegisterRequest;
import com.tpe.security.JwtUtils;
import com.tpe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserJWTController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;



    //Register *******************************************
    @PostMapping("/register") // http://localhost:8080/register + POST + JSON
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){

        userService.registerUser(request);
        String responseMessage = "User Regsitered successfully";

        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);

    }



    //LOGIN  ***********************************************
    @PostMapping("/login") // http://localhost:8080/login + POST + JSON
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRquest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request
                .getUserName(), request.getPassword()));

        String token = jwtUtils.generateToken(authentication);
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("status", "true");

        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }


}
