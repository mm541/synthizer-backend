package com.moa.synthizerbackend.user.controller;

import com.moa.synthizerbackend.common.response.ApiResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public ApiResponse<String> getUsers(Authentication authentication) {
        return new ApiResponse<>(
                true,
                "Authenticated access granted",
                authentication.getName()
        );
    }
}
