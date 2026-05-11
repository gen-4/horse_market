package com.gen_4.horse_market.services;

import com.gen_4.horse_market.models.user.User;

public interface AuthenticationService {

    public User register(String username, String password);

    public User login(String username, String password);

    public User loginWithToken(long userId);
    
}
