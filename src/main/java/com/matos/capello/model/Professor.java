package com.matos.capello.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "professor")
public class Professor extends Stakeholder {

    @Id
    @SequenceGenerator(
            name="professor_sequence",
            sequenceName="professor_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "professor_sequence"
    )
    private Long id;

    @ManyToOne
    private Institute institute;

    public Professor(String name, String email, Institute institute) {
        super(name, email);
        this.institute = institute;
    }

}
