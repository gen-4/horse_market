package com.gen_4.horse_market.dtos.auth;

import com.gen_4.horse_market.dtos.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

    private String token;
    private UserDto user;
    
}