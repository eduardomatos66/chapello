package com.matos.capello.business;

import com.matos.capello.model.Opportunity;
import com.matos.capello.repository.OpportunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OpportunityServiceTests {

    @Mock private OpportunityRepository opportunityRepository;
    private OpportunityService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new OpportunityService(opportunityRepository);
    }

    @Test
    void testGetOpportunitiesReturnsAll() {
        // when
        this.underTest.getOpportunities(null, null);

        // then
        verify(opportunityRepository).findAll();
    }

    @Test
    void testGetOpportunityByIdReturns() {
        // when
        this.underTest.getOpportunities(null, null);

        // then
        verify(opportunityRepository).findAll();
    }

    @Test
    void testAddNewOpportunityReturns() {
        // given
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

        // when
        this.underTest.addNewOpportunity(opportunity);

        // then
        ArgumentCaptor<Opportunity> opportunityArgumentCaptor = ArgumentCaptor.forClass(Opportunity.class);

        verify(opportunityRepository).save(opportunityArgumentCaptor.capture());
        Opportunity captured = opportunityArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(opportunity);
    }

    @Test
    void testDeleteOpportunityReturns() {
        // given
        long id = 10;
        given(opportunityRepository.existsById(id)).willReturn(true);

        // when
        underTest.deleteOpportunity(id);

        // then
        verify(opportunityRepository).deleteById(id);
    }

    @Test
    void testUpdateOpportunityReturns() {
        // given
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

        // when
        this.underTest.addNewOpportunity(opportunity);

        // then
        ArgumentCaptor<Opportunity> opportunityArgumentCaptor = ArgumentCaptor.forClass(Opportunity.class);

        verify(opportunityRepository).save(opportunityArgumentCaptor.capture());
        Opportunity captured = opportunityArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(opportunity);
    }
}
