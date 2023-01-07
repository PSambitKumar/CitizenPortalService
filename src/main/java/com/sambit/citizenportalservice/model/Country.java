package com.sambit.citizenportalservice.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 02/01/2023 - 12:03 AM
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "countryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;
    @Column(name = "countryName")
    private String countryName;
    @Column(name = "countryCode")
    private String countryCode;
    @Column(name = "isActive")
    private boolean isActive;
}
