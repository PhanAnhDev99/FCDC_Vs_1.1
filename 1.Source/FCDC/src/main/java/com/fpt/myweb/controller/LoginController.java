package com.fpt.myweb.controller;

import com.fpt.myweb.dto.request.LoginRequest;
import com.fpt.myweb.dto.response.CommonRes;
import com.fpt.myweb.dto.response.LoginResponse;
import com.fpt.myweb.entity.User;
import com.fpt.myweb.exception.ErrorCode;
import com.fpt.myweb.repository.UserRepository;
import org.hibernate.annotations.MetaValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login", consumes = {MediaType.ALL_VALUE})
    public ResponseEntity<CommonRes> checkLogin(LoginRequest loginRequest) {
        CommonRes commonRes = new CommonRes();
        commonRes.setResponseCode(ErrorCode.PROCESS_SUCCESS.getKey());
        commonRes.setMessage(ErrorCode.PROCESS_SUCCESS.getValue());
        User user = userRepository.findByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword());
        if(user==null){
            commonRes.setResponseCode(ErrorCode.AUTHENTICATION_FAILED.getKey());
            commonRes.setMessage(ErrorCode.AUTHENTICATION_FAILED.getValue());
        }else{
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUsername(user.getUsername());
            loginResponse.setRole(user.getRoles().getName());
            commonRes.setData(loginResponse);
        }
        return ResponseEntity.ok(commonRes);
    }
}
