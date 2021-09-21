package com.matos.capello.controller;

import com.matos.capello.CapelloApplicationTests;
import com.matos.capello.business.OpportunityService;
import com.matos.capello.exception.OpportunityNotExistentException;
import com.matos.capello.model.Opportunity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class OpportunityControllerTest extends CapelloApplicationTests {

    @Mock
    private OpportunityService opportunityService;

    private OpportunityController underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new OpportunityController(this.opportunityService);
    }

    @Test
    void testGetOpportunityByIdReturns() {
        // given
        // when
        // then
    }

    @Test
    void testGetOpportunityByKeyReturns() {
        // given
        // when
        // then
    }

    @Test
    void testRegisterNewOpportunityReturns() {
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
        this.underTest.registerNewOpportunity(opportunity);

        // then
        ArgumentCaptor<Opportunity> opportunityArgumentCaptor = ArgumentCaptor.forClass(Opportunity.class);

        verify(this.opportunityService).addNewOpportunity(opportunityArgumentCaptor.capture());
        Opportunity captured = opportunityArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(opportunity);
    }

    @Test
    void testDeleteOpportunityReturns() {
        // given
        long id = 10;
        given(this.opportunityService.getOpportunityById(id)).willReturn(new Opportunity());

        // when
        underTest.deleteOpportunity(id);

        // then
        verify(this.opportunityService).deleteOpportunity(id);
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        // given
        long id = 10;
        given(this.opportunityService.getOpportunityById(id)).willThrow(OpportunityNotExistentException.class);

        // when
        // then
        assertThatThrownBy(() -> this.underTest.deleteOpportunity(id))
                .isInstanceOf(OpportunityNotExistentException.class)
                .hasMessageContaining("The opportunity with id " + id + " does not exists");

        verify(this.opportunityService, never()).deleteOpportunity(any());
    }

    @Test
    void testUpdateOpportunityReturns() {
        // given
        // when
        // then
    }
}