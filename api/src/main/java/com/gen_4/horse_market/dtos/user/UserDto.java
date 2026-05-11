package com.gen_4.horse_market.dtos.user;

import java.util.List;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {

    private long id;
    private String username;
    private Timestamp registerDate;
    private Timestamp lastLogin;
    private boolean isBanned;
    private boolean isEnabled;
    private List<RoleDto> roles;
    
}