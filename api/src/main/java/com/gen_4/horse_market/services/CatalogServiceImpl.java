package com.gen_4.horse_market.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.gen_4.horse_market.exceptions.ParametersValidationException;
import com.gen_4.horse_market.models.catalog.Criteria;
import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.repositories.HorseRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final HorseRepository horseRepository;

    @Override
    public Horse createHorses(Horse horse) {

        try{
            horseRepository.save(horse);
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument error creating horse " + horse.getName(), e);
            throw new ParametersValidationException("Wrong argument registering user" + horse.getName(), e);
        } catch (NullPointerException e) {
            log.error("Null pointer exception creating horse", e);
            throw new ParametersValidationException("Null horse registering horse", e);
        }

        return horse;
    }
  
    @Override
    public List<Horse> getHorses(Criteria criteria) {
        return horseRepository.findAll(); // TODO: Complete this
    }

}
