package com.sambit.citizenportalservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 04/01/2023 - 7:46 PM
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "district")
public class District {
    @Id
    @Column(name = "districtId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long districtId;
    @Column(name = "districtName")
    private String districtName;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stateId")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private State state;
    @Column(name = "status")
    private boolean status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        District district = (District) o;
        return districtId != null && Objects.equals(districtId, district.districtId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
