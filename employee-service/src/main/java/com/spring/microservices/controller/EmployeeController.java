package com.spring.microservices.controller;

import com.spring.microservices.dto.EmployeeDto;
import com.spring.microservices.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeDto), HttpStatus.CREATED);
    }

    @GetMapping("/{employee-id}")
    public ResponseEntity<?> getEmployee(@PathVariable("employee-id") Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
}
