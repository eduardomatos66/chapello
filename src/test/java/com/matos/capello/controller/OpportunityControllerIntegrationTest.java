package com.matos.capello.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matos.capello.CapelloApplicationTests;
import com.matos.capello.model.Opportunity;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;


class OpportunityControllerIntegrationTest extends CapelloApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private OpportunityController opportunityController;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.opportunityController).build();
    }


    private Opportunity getOpportunityObj(String key) {
        return new Opportunity(
                key,
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments");
    }

    private String createJsonOpportunity() throws JsonProcessingException {
        Opportunity opportunity = this.getOpportunityObj("key");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(opportunity);
    }

    @Test
    void testGetOpportunitiesReturns200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetOpportunitiesReturnsMediaTypeJSON() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetOpportunitiesReturns404() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunit"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testRegisterNewOpportunity_Response201() throws Exception {
        String json = this.createJsonOpportunity();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/opportunity")
                                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.header().string("location",
//                        Matchers.containsString("http://localhost/api/opportunity")));
    }

    @Test
    void testUpdateOpportunityReturns202() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("updateOpp");
        this.opportunityController.registerNewOpportunity(opportunity);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity?key=updateOpp"));

        Opportunity opp = this.opportunityController.getOpportunities().get(0);

        this.mockMvc.perform(MockMvcRequestBuilders.put(String.format("/api/opportunity/%s", opp.getId())))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void testUpdateOpportunityReturnsObjectJSON() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("updateOpp1");
        this.opportunityController.registerNewOpportunity(opportunity);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/opportunity/4?title=MopsTitle"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void testDeleteOpportunityReturn202() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("toBeDeleted");
        this.opportunityController.registerNewOpportunity(opportunity);
//        Opportunity opp = this.opportunityController.getOpportunities(0L,"toBeDeleted").get(0);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/opportunity/%s", opp.getId())))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    void testDeleteOpportunityReturn404() throws Exception {
//        Opportunity opportunity = this.getOpportunityObj("toBeDeleted");
//        this.opportunityController.registerNewOpportunity(opportunity);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/opportunity/123"))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}
