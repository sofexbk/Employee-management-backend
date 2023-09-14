package com.ackend.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ackend.app.models.Employee;
import com.ackend.app.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    //get All employees
    @GetMapping("/employees")
    public List<Employee> getAlEmployees(){
        return employeeRepository.findAll();
    }
}
