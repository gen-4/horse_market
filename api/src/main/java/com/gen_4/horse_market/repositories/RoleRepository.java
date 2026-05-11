package com.gen_4.horse_market.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gen_4.horse_market.models.user.Role;
import com.gen_4.horse_market.models.user.RoleOptions;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Optional<Role> findByRole(RoleOptions role);
    
}

