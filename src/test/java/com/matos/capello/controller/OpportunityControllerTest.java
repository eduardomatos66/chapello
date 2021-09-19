package com.matos.capello.controller;

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


class OpportunityControllerTest extends CapelloApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private OpportunityController opportunityController;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.opportunityController).build();
    }


    private void insertOneOpportunity(String key) {
        Opportunity opportunity = new Opportunity(
                key,
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments");
        this.opportunityController.registerNewOpportunity(opportunity);
    }

    @Test
    public void testGetOpportunitiesReturns() throws Exception {
        List<Opportunity> response = this.opportunityController.getOpportunities();
        // TODO: Update with local creation besides using the currently production configuration
        Assert.assertEquals(response.size(), 2);
    }

    @Test
    public void testGetOpportunitiesReturns200() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/opportunity"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetOpportunitiesReturnsMediaTypeJSON() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/opportunity"))
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

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/opportunity"))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testGetOpportunitiesReturns404() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/opportunit"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testRegisterNewOpportunity_Response201() throws Exception {
        Opportunity opportunity = new Opportunity(
                "key",
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(opportunity);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/opportunity")
                                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated());
//                .andExpect(MockMvcResultMatchers.header().string("location",
//                        Matchers.containsString("http://localhost/api/v1/opportunity")));
    }

    @Test
    public void testUpdateOpportunityReturns202() throws Exception {
        this.insertOneOpportunity("updateOpp");
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/opportunity/3"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testUpdateOpportunityReturnsObjectJSON() throws Exception {
        this.insertOneOpportunity("updateOpp1");
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/opportunity/3?title=MopsTitle"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteOpportunityReturn202() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/opportunity/2"))
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    @Test
    public void testDeleteOpportunityReturn404() throws Exception {
//        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/opportunity/2999"))
//                .andReturn();
    }
}
