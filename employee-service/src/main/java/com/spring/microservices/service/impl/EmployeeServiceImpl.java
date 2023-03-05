package com.spring.microservices.service.impl;

import com.spring.microservices.dto.APIResponseDto;
import com.spring.microservices.dto.DepartmentDto;
import com.spring.microservices.dto.EmployeeDto;
import com.spring.microservices.entity.Employee;
import com.spring.microservices.repository.EmployeeRepository;
import com.spring.microservices.service.APIClient;
import com.spring.microservices.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final APIClient apiClient;

    private Employee mapToEmployee(EmployeeDto employeeDto) {
        return Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .email(employeeDto.getEmail())
                .departmentCode(employeeDto.getDepartmentCode())
                .build();
    }

    private EmployeeDto mapToEmployeeDto(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .departmentCode(employee.getDepartmentCode())
                .build();
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(mapToEmployee(employeeDto));
        return mapToEmployeeDto(employee);
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(mapToEmployeeDto(employee));
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long id, Exception exception) {
        Employee employee = employeeRepository.findById(id).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentDescription("Research and Development Department");
        departmentDto.setDepartmentCode("R&D001");

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(mapToEmployeeDto(employee));
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
