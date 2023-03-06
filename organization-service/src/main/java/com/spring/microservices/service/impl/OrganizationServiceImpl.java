package com.spring.microservices.service.impl;

import com.spring.microservices.dto.OrganizationDto;
import com.spring.microservices.entity.Organization;
import com.spring.microservices.repository.OrganizationRepository;
import com.spring.microservices.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;

    private Organization mapToOrganization(OrganizationDto organizationDto) {
        return Organization.builder()
                .organizationName(organizationDto.getOrganizationName())
                .organizationDescription(organizationDto.getOrganizationDescription())
                .organizationCode(organizationDto.getOrganizationCode())
                .build();
    }

    private OrganizationDto mapToOrganizationDto(Organization organization) {
        return OrganizationDto.builder()
                .id(organization.getId())
                .organizationName(organization.getOrganizationName())
                .organizationDescription(organization.getOrganizationDescription())
                .organizationCode(organization.getOrganizationCode())
                .createdDate(organization.getCreatedDate())
                .build();
    }

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = mapToOrganization(organizationDto);
        organization.setCreatedDate(LocalDateTime.now());

        organizationRepository.save(organization);

        return mapToOrganizationDto(organization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return mapToOrganizationDto(organization);
    }
}
