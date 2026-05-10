package com.gen_4.horse_exchange.dtos.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    
    private long id;
    private String role;

}