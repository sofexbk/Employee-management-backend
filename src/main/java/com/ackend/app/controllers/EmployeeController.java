package com.ackend.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ackend.app.exceptions.ResourceNotFoundException;
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

   //create empoloyee rest Api
   @PostMapping("/employees")
   public Employee createEmployee(@RequestBody Employee employee){
    return employeeRepository.save(employee);
   }

   //get employee by id rest Api
   @GetMapping("/employees/{id}")
   public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
    Employee employee = employeeRepository.findById(id)
       .orElseThrow(()->new ResourceNotFoundException("Employee doesnt exist with id :"+id));
        return ResponseEntity.ok(employee);
   }




}
