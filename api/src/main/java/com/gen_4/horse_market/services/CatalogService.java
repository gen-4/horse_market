package com.gen_4.horse_market.services;

import java.util.List;

import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.models.catalog.Criteria;

public interface CatalogService {

    public Horse createHorses();
  
    public List<Horse> getHorses(Criteria criteria);
    
}
