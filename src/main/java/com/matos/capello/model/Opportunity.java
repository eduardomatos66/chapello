package com.matos.capello.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "opportunit")
public class Opportunity {
    @Id
    @SequenceGenerator(
            name="opportunity_sequence",
            sequenceName="opportunity_sequence",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "opportunity_sequence"
    )
    private Long id;
    private String key;
    private String title;
    private String description;
    //    private List<String> keywords;
    private String progress;
    private String suggestedBy;
    private String impacted_areas;
    //    private List<String> suggestedPO;
    private String priority;
    //    private List<String> relatedLinks;
    private LocalDate registerDate;
    private String comments;


    public Opportunity(String key,
                       String title,
                       String description,
                       String progress,
                       String suggestedBy,
                       String impacted_areas,
                       String priority,
                       LocalDate registerDate,
                       String comments) {
        this.key = key;
        this.title = title;
        this.description = description;
        this.progress = progress;
        this.suggestedBy = suggestedBy;
        this.impacted_areas = impacted_areas;
        this.priority = priority;
        this.registerDate = registerDate;
        this.comments = comments;
    }
}

