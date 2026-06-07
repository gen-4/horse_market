package com.gen_4.horse_market.models.catalog;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.gen_4.horse_market.models.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Horse {
  
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

    private String name;

    private String description;

    private float height;
    
    private float weight;

    @ColumnDefault("true")
    @Column(nullable = false)
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private User owner;
    
}
