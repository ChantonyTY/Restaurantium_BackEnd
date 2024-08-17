package com.restaurantium.packages.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurantium.dto.EmployeeUpdateDTO;
import com.restaurantium.packages.Model.Employee;
import com.restaurantium.packages.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/{pkEmployee}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String pkEmployee) {
        Optional<Employee> employee = employeeService.getEmployeeById(pkEmployee);
        return employee.map(ResponseEntity::ok)
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{pkEmployee}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable String pkEmployee,
            @RequestBody EmployeeUpdateDTO updateDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(pkEmployee, updateDTO);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee)
                                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{pkEmployee}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String pkEmployee) {
        boolean isRemoved = employeeService.deleteEmployee(pkEmployee);
        return isRemoved ? ResponseEntity.noContent().build()
                         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}