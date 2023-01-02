package com.sambit.citizenportalservice.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 01/01/2023 - 4:32 PM
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "url")
public class URL {
    @Id
    @Column(name = "urlId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlId;
    @Column(name = "urlName")
    private String urlName;
    @Column(name = "routerLink")
    private String routerLink;
    @Column(name = "urlIcon")
    private String urlIcon;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "urlGroupId")
    private URLGroup urlGroup;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;
}
