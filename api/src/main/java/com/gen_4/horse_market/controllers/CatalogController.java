package com.gen_4.horse_market.controllers;

import java.net.URI;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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
        @RequestBody HorseDto horseDto,
        @RequestAttribute long userId
    ) {
        Horse horse;
        HorseDtoConversor conversor = new HorseDtoConversor();
        
        horse = catalogService.createHorses(userId, conversor.toHorse(horseDto));
        return ResponseEntity.created(URI.create("/horse/" + horse.getId()))
            .body(conversor.toHorseDto(horse));
    }

    @DeleteMapping("horse/{horseId}")
    public ResponseEntity<Void> deleteHorse(
        @PathVariable long horseId,
        @RequestAttribute long userId
    ) {
        catalogService.deleteHorse(horseId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("horses")
    public ResponseEntity<Page<HorseDto>> getHorses(
        Criteria criteria,
        Pageable pageable
    ) {
        HorseDtoConversor conversor = new HorseDtoConversor();

        return ResponseEntity.ok(conversor.toHorsesDto(catalogService.getHorses(criteria, pageable)));
    }

}
