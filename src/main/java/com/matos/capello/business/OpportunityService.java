package com.matos.capello.business;

import com.matos.capello.exception.OpportunityNotExistentException;
import com.matos.capello.model.Opportunity;
import com.matos.capello.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Slf4j
@AllArgsConstructor
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @GetMapping
    public List<Opportunity> getOpportunities() {
        return this.opportunityRepository.findAll();
    }

    public Opportunity getOpportunityById(Long opportunityId) {
        Optional<Opportunity> optionalOpportunity = this.opportunityRepository.findById(opportunityId);

        if (optionalOpportunity.isEmpty()) {
            throw new OpportunityNotExistentException(
                    String.format("There is no opportunity for id: %s", opportunityId));
        }

        return optionalOpportunity.get();
    }

    public Opportunity getOpportunityByKey(String key) {
        Opportunity opportunity = this.opportunityRepository.findOpportunityByKey(key);

        if (opportunity == null) {
            throw new OpportunityNotExistentException(
                    String.format("There is no opportunity for key: %s", key));
        }

        return opportunity;
    }

    public void addNewOpportunity(Opportunity opportunity) {
        if (this.opportunityRepository.findOpportunityByKey(opportunity.getKey()) != null) {
            throw new IllegalStateException("The opportunity's key is already registered!!");
        }
        this.opportunityRepository.save(opportunity);
    }

    public void deleteOpportunity(Long opportunityId) {
        boolean exists = this.opportunityRepository.existsById(opportunityId);
        if (!exists) {
            throw new OpportunityNotExistentException("The opportunity with id " + opportunityId + " does not exists");
        }
        this.opportunityRepository.deleteById(opportunityId);
    }

    @Transactional
    public void updateOpportunity(Long opportunityId, String title, String description) {
        Opportunity opportunity = this.getOpportunityById(opportunityId);

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
