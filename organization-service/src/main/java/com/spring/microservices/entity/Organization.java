package com.spring.microservices.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name", nullable = false)
    private String organizationName;

    @Column(name = "organization_description")
    private String organizationDescription;

    @Column(name = "organization_code", nullable = false, unique = true)
    private String organizationCode;

    @CreationTimestamp
    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;
}
