package com.matos.capello.business;

import com.matos.capello.CapelloApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class OpportunityServiceTests extends CapelloApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private OpportunityService opportunityService;

}
