package com.gen_4.horse_market.repositories;

import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query(value = """
        SELECT *
        FROM horse h
        WHERE (:minWeight IS NULL OR h.weight >= :minWeight)
        AND (:maxWeight IS NULL OR h.weight <= :maxWeight)
        AND (:minHeight IS NULL OR h.height >= :minHeight)
        AND (:maxHeight IS NULL OR h.height <= :maxHeight)
        AND (
            :description IS NULL OR
            to_tsvector('english', h.description)
            @@ websearch_to_tsquery('english', :description)
        )
        ORDER BY
            CASE
                WHEN :description IS NULL THEN 0
                ELSE ts_rank(
                    to_tsvector('english', h.description),
                    websearch_to_tsquery('english', :description)
                )
            END DESC
    """,
    nativeQuery = true)
    Page<Horse> search(
        @Param("minWeight") Float minWeight,
        @Param("maxWeight") Float maxWeight,
        @Param("minHeight") Float minHeight,
        @Param("maxHeight") Float maxHeight,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice,
        @Param("description") String description,
        Pageable pageable
    );
    
}
