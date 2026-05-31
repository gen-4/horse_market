package com.gen_4.horse_market.services;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gen_4.horse_market.exceptions.DuplicatedEntityException;
import com.gen_4.horse_market.exceptions.NotFoundException;
import com.gen_4.horse_market.exceptions.ParametersValidationException;
import com.gen_4.horse_market.exceptions.UnauthorizedException;
import com.gen_4.horse_market.models.user.Role;
import com.gen_4.horse_market.models.user.RoleOptions;
import com.gen_4.horse_market.models.user.User;
import com.gen_4.horse_market.repositories.RoleRepository;
import com.gen_4.horse_market.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String username, String password) {
        User user = null;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Optional<Role> userRole = roleRepository.findByRole(RoleOptions.USER);
        if (!userRole.isPresent()) {
            throw new NotFoundException("Role " + RoleOptions.USER.name());
        }

        try {
            user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .registerDate(now)
                .lastLogin(now)
                .isBanned(false)
                .isEnabled(true)
                .roles(List.of(userRole.get()))
                .build();
        
            userRepository.save(user);
           
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity error registering user " + username, e);
            throw new DuplicatedEntityException("Duplicate user " + username, e);
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument error registering user " + username, e);
            throw new ParametersValidationException("Wrong argument registering user" + username, e);
        } catch (NullPointerException e) {
            log.error("Null pointer exception registering user " + username, e);
            throw new ParametersValidationException("Null user registering user " + username, e);
        }
        
        return user;
    }

    @Override
    public User login(String username, String password) {
        Optional<User> optionalUser;
        User user;

        optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            log.error("No user found when attempting to login username " + username);
            throw new NotFoundException("User" + username);
        }
        
        user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword()) || user.isBanned()) {
            log.error("Password do not match when attempting to login user " + username);
            throw new UnauthorizedException(username);
        }

        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User loginWithToken(long userId) {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            log.error("No user found when attempting to login user " + userId);
            throw new NotFoundException("User " + userId);
        }

        User user = optionalUser.get();
        
        if (user.isBanned()) {
            log.error("Denied login to user " + user.getUsername() + " due to being banned");
            throw new UnauthorizedException(user.getUsername());
        }

        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return user;
    }    

}

