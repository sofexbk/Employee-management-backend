package com.ackend.app.controllers;

import java.util.HashMap;
import java.util.List;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

   //update employee rest Api
   @PutMapping("/employees/{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails){
    Employee employee = employeeRepository.findById(id)
           .orElseThrow(()->new ResourceNotFoundException("Employee doesnt exist with id :"+id));
     employee.setFirstName(employeeDetails.getFirstName());
     employee.setLastName(employeeDetails.getLastName());
     employee.setEmailId(employeeDetails.getEmailId()); 
     Employee updatedEmployee=employeeRepository.save(employee);
     return ResponseEntity.ok(updatedEmployee);
   }
   //delete employee rest Api
    @DeleteMapping("/employees/{id}")
     public ResponseEntity<java.util.Map<String, Boolean>> deleteEmployee (@PathVariable Long id){
      Employee employee = employeeRepository.findById(id)
           .orElseThrow(()->new ResourceNotFoundException("Employee doesnt exist with id :"+id));

        employeeRepository.delete(employee);
        java.util.Map<String, Boolean> response = new java.util.HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
     }
}
