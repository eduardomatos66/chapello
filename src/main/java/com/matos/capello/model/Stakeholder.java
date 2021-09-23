package com.matos.capello.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "stakeholder")
public abstract class Stakeholder {
    @Id
    @SequenceGenerator(
            name="stakeholder_sequence",
            sequenceName="stakeholder_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stakeholder_sequence"
    )
    private Long id;
    private String name;
    private String email;


    public Stakeholder(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
