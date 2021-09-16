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

    @DeleteMapping(path = "{oportunityId}")
    public void deleteOpportunity(@PathVariable("oportunityId") Long opportunityId) {
        this.opportunityService.deleteOpportunity(opportunityId);
    }

    @PutMapping(path = "{oportunityId}")
    public void updateOpportunity(@PathVariable("oportunityId") Long opportunityId,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description) {

        this.opportunityService.updateOpportunity(opportunityId, title, description);
    }
}
