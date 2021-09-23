package com.matos.capello.controller;

import com.matos.capello.model.Opportunity;
import com.matos.capello.business.OpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/opportunity")
@AllArgsConstructor
public class OpportunityController extends AbstractController {

    private final OpportunityService opportunityService;

    @GetMapping(path="")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Opportunity> getOpportunities() {

        return this.opportunityService.getOpportunities();
    }

    @GetMapping(path="/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Opportunity getOpportunityById(@PathVariable("id")  Long id) {

        return this.opportunityService.getOpportunityById(id);
    }

    @GetMapping(path="", params = "key")
    @ResponseStatus(value = HttpStatus.OK)
    public Opportunity getOpportunityByKey(@RequestParam("key") String key) {

        return this.opportunityService.getOpportunityByKey(key);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void registerNewOpportunity(@RequestBody Opportunity opportunity) {
        this.opportunityService.addNewOpportunity(opportunity);
    }

    @DeleteMapping(path = "/{oportunityId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteOpportunity(@PathVariable("oportunityId") Long opportunityId) {
        this.opportunityService.deleteOpportunity(opportunityId);
    }

    @PutMapping(path = "/{oportunityId}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateOpportunity(@PathVariable("oportunityId") Long opportunityId,
                                  @RequestParam(required = false) String title,
                                  @RequestParam(required = false) String description) {

        this.opportunityService.updateOpportunity(opportunityId, title, description);
    }
}
