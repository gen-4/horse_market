package com.gen_4.horse_market.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.models.catalog.Criteria;

public interface CatalogService {

    public Horse createHorses(long userId, Horse horse);
  
    public void deleteHorse(long userId, long horseId);
    
    public Page<Horse> getHorses(Criteria criteria, Pageable pageable);
    
}
