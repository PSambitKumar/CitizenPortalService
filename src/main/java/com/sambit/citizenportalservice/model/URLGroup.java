package com.sambit.citizenportalservice.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Project : CitizenPortalService
 * @Auther : Sambit Kumar Pradhan
 * @Created On : 01/01/2023 - 4:39 PM
 */
@Data
@Entity
@Table(name = "urlGroup")
public class URLGroup {
    @Id
    @Column(name = "urlGroupId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long urlGroupId;
    @Column(name = "urlGroupName")
    private String urlGroupName;
    @Column(name = "urlGroupIcon")
    private String urlGroupIcon;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private boolean status;
}
