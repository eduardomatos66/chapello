package com.matos.capello.business;

import com.matos.capello.exception.OpportunityNotExistentException;
import com.matos.capello.model.Opportunity;
import com.matos.capello.repository.OpportunityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class OpportunityServiceTests {

    @Mock private OpportunityRepository opportunityRepository;
    private OpportunityService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new OpportunityService(this.opportunityRepository);
    }

    @Test
    void testGetOpportunities_accessFindAll() {
        // when
        this.underTest.getOpportunities();

        // then
        verify(this.opportunityRepository).findAll();
    }

    @Test
    void testGetOpportunityById_accessFindById() {
        // given
        Long id = 99L;
        // given
        Opportunity opportunity = new Opportunity(
                id,
                "key",
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments");
        given(this.opportunityRepository.findById(id)).willReturn(java.util.Optional.of(opportunity));

        // when
        this.underTest.getOpportunityById(id);

        // then
        verify(this.opportunityRepository).findById(id);
    }

    @Test
    void testGetOpportunityByKey_accessFindOpportunityByKey() {
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
        verify(this.opportunityRepository).findOpportunityByKey("key");
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

        verify(this.opportunityRepository).save(opportunityArgumentCaptor.capture());
        Opportunity captured = opportunityArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(opportunity);
    }

    @Test
    void testDeleteOpportunityReturns() {
        // given
        Long id = 10L;
        given(this.opportunityRepository.existsById(id)).willReturn(true);

        // when
        this.underTest.deleteOpportunity(id);

        // then
        verify(this.opportunityRepository).deleteById(id);
    }

    @Test
    void testDeleteOpportunityWillThrowOpportunityNotExistentException() {
        // given
        long id = 10;
        given(this.opportunityRepository.existsById(id)).willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> this.underTest.deleteOpportunity(id))
                .isInstanceOf(OpportunityNotExistentException.class)
                .hasMessageContaining(String.format("The opportunity with id %s does not exists", id));

        verify(this.opportunityRepository, never()).deleteById(any());
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

        verify(this.opportunityRepository).save(opportunityArgumentCaptor.capture());
        Opportunity captured = opportunityArgumentCaptor.getValue();
        assertThat(captured).isEqualTo(opportunity);
    }
}
