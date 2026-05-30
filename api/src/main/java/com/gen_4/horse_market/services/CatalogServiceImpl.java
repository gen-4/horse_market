package com.gen_4.horse_market.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.gen_4.horse_market.models.catalog.Criteria;
import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.repositories.HorseRepository;


@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final HorseRepository horseRepository;

    @Override
    public Horse createHorses() {
        Horse horse;
        horse = Horse.builder()
            .build(); // TODO: Complete this

        horseRepository.save(horse);

        return horse;
    }
  
    @Override
    public List<Horse> getHorses(Criteria criteria) {
        return horseRepository.findAll(); // TODO: Complete this
    }

}
