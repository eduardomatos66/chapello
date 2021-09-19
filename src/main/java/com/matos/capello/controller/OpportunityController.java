package com.matos.capello.controller;

import com.matos.capello.model.Opportunity;
import com.matos.capello.business.OpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/opportunity")
public class OpportunityController extends AbstractController {

    private final OpportunityService opportunityService;

    @Autowired
    public OpportunityController(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<Opportunity> getOpportunities(@PathVariable(value = "oportunityId", required = false) Long opportunityId,
                                              @RequestParam(required = false) String key) {

        return this.opportunityService.getOpportunities(opportunityId, key);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerNewOpportunity(@RequestBody Opportunity opportunity) {
        this.opportunityService.addNewOpportunity(opportunity);
    }

    @DeleteMapping(path = "{oportunityId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteOpportunity(@PathVariable("oportunityId") Long opportunityId) {
        this.opportunityService.deleteOpportunity(opportunityId);
    }

    @PutMapping(path = "{oportunityId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateOpportunity(@PathVariable("oportunityId") Long opportunityId,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description) {

        this.opportunityService.updateOpportunity(opportunityId, title, description);
    }
}
