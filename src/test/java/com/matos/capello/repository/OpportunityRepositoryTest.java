package com.matos.capello.repository;

import com.matos.capello.CapelloApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityRepositoryTest extends CapelloApplicationTests {


    private MockMvc mockMvc;

    @Autowired
    private OpportunityRepository opportunityRepository;

}