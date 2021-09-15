package com.matos.capello.opportunity;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


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

    public Opportunity() {
    }

    public Opportunity(String key, String title, String description, String progress, String suggestedBy, String impacted_areas, String priority, LocalDate registerDate, String comments) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getSuggestedBy() {
        return suggestedBy;
    }

    public void setSuggestedBy(String suggestedBy) {
        this.suggestedBy = suggestedBy;
    }

    public String getImpacted_areas() {
        return impacted_areas;
    }

    public void setImpacted_areas(String impacted_areas) {
        this.impacted_areas = impacted_areas;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Opportunity{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", progress='" + progress + '\'' +
                ", suggestedBy='" + suggestedBy + '\'' +
                ", impacted_areas='" + impacted_areas + '\'' +
                ", priority='" + priority + '\'' +
                ", registerDate=" + registerDate +
                ", comments='" + comments + '\'' +
                '}';
    }
}
