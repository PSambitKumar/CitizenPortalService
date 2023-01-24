package com.sambit.citizenportalservice.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 02/01/2023 - 8:23 PM
 */
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "state")
public class State {
    @Id
    @Column(name = "stateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;
    @Column(name = "stateName")
    private String StateName;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "countryId")
    @ToString.Exclude
    private Country country;
    @Column(name = "isActive")
    private boolean isActive;
}
