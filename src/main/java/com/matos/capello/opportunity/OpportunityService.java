package com.matos.capello.opportunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}
