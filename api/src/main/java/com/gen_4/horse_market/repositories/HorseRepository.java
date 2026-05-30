package com.gen_4.horse_market.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.gen_4.horse_market.models.catalog.Horse;

public interface HorseRepository extends JpaRepository<Horse, Long> {}
