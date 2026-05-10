package com.gen_4.horse_exchange.services;

import com.gen_4.horse_exchange.models.user.User;

public interface AuthenticationService {

    public User register(String username, String password);

    public User login(String username, String password);

    public User loginWithToken(long userId);
    
}
