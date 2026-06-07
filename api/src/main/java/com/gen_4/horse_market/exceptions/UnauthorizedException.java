package com.gen_4.horse_market.exceptions;

public class UnauthorizedException extends RuntimeException {
  
    public UnauthorizedException(String username) {
        super("Unauthorized username " + username);
    }

}
