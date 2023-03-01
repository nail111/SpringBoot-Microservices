package com.spring.microservices.service.impl;

import com.spring.microservices.dto.DepartmentDto;
import com.spring.microservices.entity.Department;
import com.spring.microservices.repository.DepartmentRepository;
import com.spring.microservices.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    private Department mapToDepartment(DepartmentDto departmentDto) {
        return Department.builder()
                .id(departmentDto.getId())
                .departmentName(departmentDto.getDepartmentName())
                .departmentDescription(departmentDto.getDepartmentDescription())
                .departmentCode(departmentDto.getDepartmentCode())
                .build();
    }

    private DepartmentDto mapToDepartmentDto(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .departmentName(department.getDepartmentName())
                .departmentDescription(department.getDepartmentDescription())
                .departmentCode(department.getDepartmentCode())
                .build();
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentRepository.save(mapToDepartment(departmentDto));
        return mapToDepartmentDto(department);
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);
        return mapToDepartmentDto(department);
    }
}
