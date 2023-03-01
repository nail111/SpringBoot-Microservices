package com.spring.microservices.controller;

import com.spring.microservices.dto.DepartmentDto;
import com.spring.microservices.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<?> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{department-code}")
    public ResponseEntity<?> getDepartment(@PathVariable("department-code") String departmentCode) {
        return ResponseEntity.ok(departmentService.getDepartmentByCode(departmentCode));
    }
}
