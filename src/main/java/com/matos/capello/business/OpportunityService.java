package com.matos.capello.business;

import com.matos.capello.model.Opportunity;
import com.matos.capello.repository.OpportunityRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @GetMapping
    public List<Opportunity> getOpportunities(Long id, String key) {
        List<Opportunity> opportunities = new ArrayList<>();
        if (Strings.isNotBlank(key)) {
            Opportunity opportunity = this.opportunityRepository.findOpportunityByKey(key).orElse(null);
            opportunities.add(opportunity);
        } else if (id != null && id > 0) {
            Opportunity opportunity = this.opportunityRepository.findById(id).orElse(null);
            opportunities.add(opportunity);
        } else {
            opportunities = this.opportunityRepository.findAll();
        }

        return opportunities;
    }

    public Optional<Opportunity> getOpportunityById(Long opportunityId) {
        return this.opportunityRepository.findById(opportunityId);
    }

    public Optional<Opportunity> getOpportunityByKey(String opportunityKey) {
        return this.opportunityRepository.findOpportunityByKey(opportunityKey);
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
        Opportunity opportunity = this.getOpportunityById(opportunityId)
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
