package com.matos.capello.opportunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @GetMapping
    public List<Opportunity> getOpportunities() {
        return this.opportunityRepository.findAll();
    }

    public void addNewOpportunity(Opportunity opportunity) {
        if (this.opportunityRepository.findOpportunityByKey(opportunity.getKey()).isPresent()) {
            throw new IllegalStateException("The opportunity's key is already registered!!");
        }
        this.opportunityRepository.save(opportunity);
    }

    public void deleteOpportunity(Long opportunityId) {
        boolean exists = this.opportunityRepository.existsById(opportunityId);
        if (!exists) {
            throw new IllegalStateException("The opportunity with id " + opportunityId + " does not exists");
        }
        this.opportunityRepository.deleteById(opportunityId);
    }

    @Transactional
    public void updateOpportunity(Long opportunityId, String title, String description) {
        Opportunity opportunity = this.opportunityRepository.findById(opportunityId)
                .orElseThrow(() -> new IllegalStateException(
                        "The opportunity with id " + opportunityId + " does not exists"
                ));

        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(opportunity.getTitle(), title)) {
            opportunity.setTitle(title);
        }

        if (description != null &&
                description.length() > 0 &&
                !Objects.equals(opportunity.getDescription(), description)) {

            opportunity.setDescription(description);
        }
    }
}
