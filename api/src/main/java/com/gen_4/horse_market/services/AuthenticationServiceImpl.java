package com.gen_4.horse_market.services;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// import com.gen_4.horse_market.exceptions.DuplicatedEntityException;
// import com.gen_4.horse_market.exceptions.NotFoundException;
// import com.gen_4.horse_market.exceptions.UnauthorizedException;
// import com.gen_4.horse_market.exceptions.WrongParametersException;
import com.gen_4.horse_market.models.user.Role;
import com.gen_4.horse_market.models.user.RoleOptions;
import com.gen_4.horse_market.models.user.User;
import com.gen_4.horse_market.repositories.RoleRepository;
import com.gen_4.horse_market.repositories.UserRepository;

import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String username, String password) {
        // throws NotFoundException, WrongParametersException, DuplicatedEntityException {
        User user = null;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Optional<Role> userRole = roleRepository.findByRole(RoleOptions.USER);
        if (!userRole.isPresent()) {
            // throw new NotFoundException("Role", RoleOptions.USER.name());
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
            
        // TODO: Log these errors
        } catch (DataIntegrityViolationException e) {
            // throw new DuplicatedEntityException("User Register Request");
        } catch (IllegalArgumentException e) {
            // throw new WrongParametersException("User Register Request");
        } catch (NullPointerException e) {
            // throw new WrongParametersException("User Register Request");
        }
        
        return user;
    }

    @Override
    public User login(String username, String password) {
        // throws NotFoundException, WrongParametersException, UnauthorizedException {
        Optional<User> optionalUser;
        User user;

        optionalUser = userRepository.findByUsername(username);
        if (!optionalUser.isPresent()) {
            // throw new NotFoundException("User", username);
        }
        
        user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword()) || user.isBanned()) {
            // throw new UnauthorizedException(username);
        }

        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return user;
    }

    @Override
    public User loginWithToken(long userId) {
        // throws NotFoundException, UnauthorizedException {

        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            // throw new NotFoundException("User", "id");
        }

        User user = optionalUser.get();
        
        if (user.isBanned()) {
            // throw new UnauthorizedException(user.getUsername());
        }

        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        return user;
    }    

}

