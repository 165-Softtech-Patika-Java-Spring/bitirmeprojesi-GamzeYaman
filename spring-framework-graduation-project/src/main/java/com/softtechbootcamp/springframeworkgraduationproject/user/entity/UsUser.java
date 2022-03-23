package com.softtechbootcamp.springframeworkgraduationproject.user.entity;


import com.softtechbootcamp.springframeworkgraduationproject.general.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "US_USER")
@Getter
@Setter
public class UsUser extends BaseEntity {

    @Id
    @SequenceGenerator(name = "UsUser", sequenceName = "US_USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "UsUser")
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "SURNAME", length = 50)
    private String surname;

    @Column(name = "USERNAME", length = 20, nullable = false)
    private String username;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;
}
