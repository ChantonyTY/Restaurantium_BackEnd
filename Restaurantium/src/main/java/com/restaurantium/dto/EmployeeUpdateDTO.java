package com.restaurantium.dto;

import lombok.Data;

@Data
public class EmployeeUpdateDTO {
    private String name;
    private String positionName;
    private Integer positionLevel;
    private String restaurantPk;
}