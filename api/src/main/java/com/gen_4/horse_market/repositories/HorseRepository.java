package com.gen_4.horse_market.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.models.user.User;

public interface HorseRepository extends JpaRepository<Horse, Long> {

    public Optional<Horse> findByIdAndOwner(long id, User owner);

    @Transactional
    @Modifying
    @Query("""
        DELETE FROM Horse h
        WHERE h.id = :horseId
        AND h.owner.id = :userId
    """)
    int deleteByIdAndOwnerId(long horseId, long ownerId);
    
}
