package com.gen_4.horse_exchange.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.gen_4.horse_exchange.models.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);
    
}
