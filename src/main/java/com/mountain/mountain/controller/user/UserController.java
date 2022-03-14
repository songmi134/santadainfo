package com.mountain.mountain.controller.user;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.mountain.mountain.controller.user.dto.RegisterUserDTO;
import com.mountain.mountain.controller.user.dto.UserDTO;
import com.mountain.mountain.domain.user.model.User;
import com.mountain.mountain.domain.user.service.UserService;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import com.mountain.mountain.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    FirebaseAuth firebaseAuth;

    @Autowired
    private UserService userService;

    //회원 등록
    @PostMapping("")
    public void register(@RequestHeader("Authorization") String authorization,
                         @RequestBody RegisterUserDTO registerUserDTO) {

        FirebaseToken decodedToken;
        String uid;
        
        //토큰검증
        try {
        String token = RequestUtil.getAuthorizationToken(authorization);

            //테스트 시 검증 스킵
            if (token.startsWith("test")){

                //랜덤 스트링을 생성해 uid 생성
                int leftLimit = 97;
                int rightLimit = 122;
                int length = 10;
                Random random = new Random();
                String generatedString = random.ints(leftLimit,rightLimit + 1)
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                        
                uid = generatedString;

                userService.register(uid, registerUserDTO.getName());

            } else {
                decodedToken = firebaseAuth.verifyIdToken(token);
                log.info("1");
                uid = decodedToken.getUid();
                log.info("2");
            }
        } catch (IllegalArgumentException | FirebaseAuthException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");

        }

        // 등록된 사용자인지 조회
        try {
            userService.loadUserByUsername(uid);
            log.info("3");
            throw new CustomException(ErrorCode.EXIST_USER);
        } catch (CustomException e) {
            // 등록안된 사용자
        }

        // 사용자 등록
        userService.register(
                uid,
                registerUserDTO.getName()
        );

    }


    // 로그인
    @GetMapping("/me")
    public UserDTO login(Authentication authentication) {
        User loginUser = (User) authentication.getPrincipal();
        return new UserDTO(loginUser);
    }


}