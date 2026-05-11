package com.gen_4.horse_market.configuration;

import java.util.List;

import lombok.Builder;
import lombok.Data;

import com.gen_4.horse_market.models.user.RoleOptions;

@Data
@Builder
public class JwtInfo {
	private Long userId;

	private String username;

	private List<RoleOptions> roles;
}
