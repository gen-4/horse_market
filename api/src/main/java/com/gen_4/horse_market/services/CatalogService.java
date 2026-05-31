package com.gen_4.horse_market.services;

import java.util.List;

import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.models.catalog.Criteria;

public interface CatalogService {

    public Horse createHorses(long userId, Horse horse);
  
    public void deleteHorse(long userId, long horseId);
    
    public List<Horse> getHorses(Criteria criteria);
    
}
