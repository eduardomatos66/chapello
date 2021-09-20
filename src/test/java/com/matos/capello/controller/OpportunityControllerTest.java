package com.matos.capello.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matos.capello.CapelloApplicationTests;
import com.matos.capello.model.Opportunity;
import org.hamcrest.Matchers;
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


class OpportunityControllerTest extends CapelloApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private OpportunityController opportunityController;

    @BeforeEach
    public void setUp() {
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
    public void testGetOpportunitiesReturns() throws Exception {
        List<Opportunity> response = this.opportunityController.getOpportunities(null, null);
        // TODO: Update with local creation besides using the currently production configuration
        Assert.assertTrue(response.size() >= 2);
    }

    @Test
    public void testGetOpportunitiesReturns200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetOpportunitiesReturnsMediaTypeJSON() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity"))
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetOpportunitiesReturnsValue() throws Exception {
        // TODO: Update with local creation besides using the currently production configuration
        String expectedResult = "[{\"id\":1,\"key\":\"OPP-0001\",\"title\":\"Title\",\"description\":\"Description\"," +
                "\"progress\":\"ON GOING\",\"suggestedBy\":\"ematos\",\"impacted_areas\":\"smoke test\",\"priority\":" +
                "\"high\",\"registerDate\":[2020,6,1],\"comments\":\"\"},{\"id\":2,\"key\":\"OPP-0002\",\"title\":\"" +
                "Title2\",\"description\":\"Description2\",\"progress\":\"DONE\",\"suggestedBy\":\"raphaelf\",\"impac" +
                "ted_areas\":\"research test\",\"priority\":\"high\",\"registerDate\":[2020,8,21],\"comments\":\"\"}]";

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString(expectedResult)));
    }

    @Test
    public void testGetOpportunitiesReturns404() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunit"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testRegisterNewOpportunity_Response201() throws Exception {
        String json = this.createJsonOpportunity();

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/opportunity")
                                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.header().string("location",
//                        Matchers.containsString("http://localhost/api/opportunity")));
    }

    @Test
    public void testUpdateOpportunityReturns202() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("updateOpp");
        this.opportunityController.registerNewOpportunity(opportunity);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/opportunity/0?key=updateOpp"));

//        Opportunity opp = this.opportunityController.getOpportunities(0L, ).get(0);

//        this.mockMvc.perform(MockMvcRequestBuilders.put(String.format("/api/opportunity/%s", opp.getId())))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testUpdateOpportunityReturnsObjectJSON() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("updateOpp1");
        this.opportunityController.registerNewOpportunity(opportunity);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/opportunity/4?title=MopsTitle"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteOpportunityReturn202() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("toBeDeleted");
        this.opportunityController.registerNewOpportunity(opportunity);
//        Opportunity opp = this.opportunityController.getOpportunities(0L,"toBeDeleted").get(0);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/opportunity/%s", opp.getId())))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteOpportunityReturn404() throws Exception {
        Opportunity opportunity = this.getOpportunityObj("toBeDeleted");
//        this.opportunityController.registerNewOpportunity(opportunity);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/opportunity/123"))
//                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }
}
