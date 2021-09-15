package com.matos.capello.opportunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/opportunity")
public class OpportunityController {

    private final OpportunityService opportunityService;

    @Autowired
    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping
    public List<Opportunity> getOpportunities() {
        return this.opportunityService.getOpportunities();
    }

    @PostMapping
    public void registerNewOpportunity(@RequestBody Opportunity opportunity) {
        this.opportunityService.addNewOpportunity(opportunity);
    }

}
