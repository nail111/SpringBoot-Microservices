package com.spring.microservices.controller;

import com.spring.microservices.dto.OrganizationDto;
import com.spring.microservices.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> saveOrganization(@RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<>(organizationService.saveOrganization(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{organization-code}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getOrganizationByCode(@PathVariable("organization-code") String organizationCode) {
        return ResponseEntity.ok(organizationService.getOrganizationByCode(organizationCode));
    }
}
