package com.gen_4.horse_market.controllers;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gen_4.horse_market.dtos.auth.AuthenticationResponseDto;
import com.gen_4.horse_market.dtos.user.RoleDto;
import com.gen_4.horse_market.dtos.user.UserDto;
import com.gen_4.horse_market.models.user.User;
import com.gen_4.horse_market.services.AuthenticationService;
import com.gen_4.horse_market.configuration.JwtInfo;
import com.gen_4.horse_market.configuration.JwtService;
import com.gen_4.horse_market.dtos.auth.AuthenticationRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthContoller {

    private final AuthenticationService authenticationService;

    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(
            @RequestBody AuthenticationRequestDto request) {
        User user = authenticationService.register(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(AuthenticationResponseDto.builder()
                .user(UserDto.builder()
                        .username(user.getUsername())
                        .registerDate(user.getRegisterDate())
                        .lastLogin(user.getLastLogin())
                        .roles(user.getRoles().stream()
                                .map(role -> RoleDto.builder()
                                        .id(role.getId())
                                        .role(role.getRole().name())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .token(generateServiceToken(user))
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(
            @RequestBody AuthenticationRequestDto request) {
        User user = authenticationService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(AuthenticationResponseDto.builder()
                .user(UserDto.builder()
                        .username(user.getUsername())
                        .registerDate(user.getRegisterDate())
                        .lastLogin(user.getLastLogin())
                        .roles(user.getRoles().stream()
                                .map(role -> RoleDto.builder()
                                        .id(role.getId())
                                        .role(role.getRole().name())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .token(generateServiceToken(user))
                .build());
    }

    @PostMapping("/login-with-token")
    public ResponseEntity<AuthenticationResponseDto> loginWithToken(@RequestAttribute long userId) {
        User user = authenticationService.loginWithToken(userId);

        return ResponseEntity.ok(AuthenticationResponseDto.builder()
                .user(UserDto.builder()
                        .username(user.getUsername())
                        .registerDate(user.getRegisterDate())
                        .lastLogin(user.getLastLogin())
                        .roles(user.getRoles().stream()
                                .map(role -> RoleDto.builder()
                                        .id(role.getId())
                                        .role(role.getRole().name())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .build());
    }

    private String generateServiceToken(User user) {

        JwtInfo jwtInfo = JwtInfo.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles().stream()
                        .map(role -> role.getRole())
                        .collect(Collectors.toList()))
                .build();

        return jwtService.generateToken(jwtInfo);

    }

}
