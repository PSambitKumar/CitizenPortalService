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
    @Column(name = "isoFullName")
    private String isoFullName;
    @Column(name = "capital")
    private String capital;
    @Column(name = "currencyName")
    private String currencyName;
    @Column(name = "currencyCode")
    private String currencyCode;
    @Column(name = "phoneCode")
    private String phoneCode;
    @Column(name = "pupulation")
    private String population;
    @Column(name = "populationPercentage")
    private String populationPercentage;
    @Column(name = "nativeLanguage")
    private String nativeLanguage;
    @Column(name = "region")
    private String region;
    @Column(name = "isActive")
    private boolean isActive;
}
