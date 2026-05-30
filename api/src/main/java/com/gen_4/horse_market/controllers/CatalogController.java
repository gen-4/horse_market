package com.gen_4.horse_market.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

import com.gen_4.horse_market.dtos.catalog.HorseDto;
import com.gen_4.horse_market.dtos.catalog.HorseDtoConversor;
import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.models.catalog.Criteria;
import com.gen_4.horse_market.services.CatalogService;


@RestController
@CrossOrigin
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;
  
    @PostMapping("horse")
    public ResponseEntity<HorseDto> createHorse(
        @RequestBody HorseDto horseDto
    ) {
        Horse horse;
        HorseDtoConversor conversor = new HorseDtoConversor();
        
        horse = catalogService.createHorses(conversor.toHorse(horseDto));
        return ResponseEntity.created(URI.create("/horse/" + horse.getId()))
            .body(conversor.toHorseDto(horse));
    }

    @GetMapping("horses")
    public ResponseEntity<List<HorseDto>> getHorses() { // TODO: Get criteria from params
        HorseDtoConversor conversor = new HorseDtoConversor();
        Criteria criteria = Criteria.builder().build();

        return ResponseEntity.ok(conversor.toHorsesDto(catalogService.getHorses(criteria)));
    }

}
