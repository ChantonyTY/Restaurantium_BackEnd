package com.restaurantium.packages.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Employee {

    @Id
    private String pkEmployee;

    private String name;
    @Column(name="position_name")
    private String positionName;
    @Column(name="position_level")
    private Integer positionLevel;

    @ManyToOne
    @JoinColumn(name = "fk_restaurant")
    private Restaurant restaurant;
}