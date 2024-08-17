package com.restaurantium.packages.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantium.dto.EmployeeUpdateDTO;
import com.restaurantium.packages.Model.Employee;
import com.restaurantium.packages.Model.Restaurant;
import com.restaurantium.packages.Repository.EmployeeRepository;
import com.restaurantium.packages.Repository.RestaurantRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;
    
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(String pkEmployee) {
        return employeeRepository.findById(pkEmployee);
    }

    public Employee updateEmployee(String pkEmployee, EmployeeUpdateDTO updateDTO) {
        return employeeRepository.findById(pkEmployee)
                .map(employee -> {
                    if (updateDTO.getName() != null) {
                        employee.setName(updateDTO.getName());
                    }
                    if (updateDTO.getPositionName() != null) {
                        employee.setPositionName(updateDTO.getPositionName());
                    }
                    if (updateDTO.getPositionLevel() != null) {
                        employee.setPositionLevel(updateDTO.getPositionLevel());
                    }
                    if (updateDTO.getRestaurantPk() != null) {
                        Optional<Restaurant> restaurant = restaurantRepository.findById(updateDTO.getRestaurantPk());
                        restaurant.ifPresent(employee::setRestaurant);
                    }
                    return employeeRepository.save(employee);
                })
                .orElse(null);
    }

    public boolean deleteEmployee(String pkEmployee) {
        if (employeeRepository.existsById(pkEmployee)) {
            employeeRepository.deleteById(pkEmployee);
            return true;
        } else {
            return false;
        }
    }
}