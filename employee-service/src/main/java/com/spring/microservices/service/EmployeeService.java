package com.spring.microservices.service;

import com.spring.microservices.dto.APIResponseDto;
import com.spring.microservices.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long id);
}
