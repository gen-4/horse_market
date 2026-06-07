package com.gen_4.horse_market.services;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.gen_4.horse_market.exceptions.NotFoundException;
import com.gen_4.horse_market.exceptions.ParametersValidationException;
import com.gen_4.horse_market.models.catalog.Criteria;
import com.gen_4.horse_market.models.catalog.Horse;
import com.gen_4.horse_market.models.user.User;
import com.gen_4.horse_market.repositories.HorseRepository;
import com.gen_4.horse_market.repositories.UserRepository;


@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final HorseRepository horseRepository;

    private final UserRepository userRepository;

    @Override
    public Horse createHorses(long userId, Horse horse) {
        Optional<User> user = userRepository.findById(userId);

        if (!user.isPresent()) {
            log.error("User not found for id " + userId);
            throw new NotFoundException("User not found for id " + userId);
        }
        horse.setOwner(user.get());

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

    @Transactional
    @Override
    public void deleteHorse(long userId, long horseId) {
        int deleted = horseRepository.deleteByIdAndOwnerId(horseId, userId);

        if (deleted == 0) {
            throw new NotFoundException("Horse not found for id " + horseId + " and user " + userId);
        }
    }
  
    @Override
    public Page<Horse> getHorses(Criteria criteria, Pageable pageable) {
        return horseRepository.search(
            criteria.getMinWeight(),
            criteria.getMaxWeight(),
            criteria.getMinHeight(),
            criteria.getMaxHeight(),
            criteria.getMinPrice(),
            criteria.getMaxPrice(),
            criteria.getDescription(),
            pageable
        );
    }

}
