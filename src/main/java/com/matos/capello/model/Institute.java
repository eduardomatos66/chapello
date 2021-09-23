package com.matos.capello.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "institute")
public class Institute {

    @Id
    @SequenceGenerator(
            name="institute_sequence",
            sequenceName="institute_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "institute_sequence"
    )
    private Long id;
    private String shortName;
    private String longName;
    @OneToMany
    private List<Professor> professorList;

    public Institute(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

}
