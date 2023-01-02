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
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "state")
public class State {
    @Id
    @Column(name = "stateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;
    @Column(name = "stateName")
    private String StateName;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "countryId")
    private Country country;
    @Column(name = "isActive")
    private boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        State state = (State) o;
        return stateId != null && Objects.equals(stateId, state.stateId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
