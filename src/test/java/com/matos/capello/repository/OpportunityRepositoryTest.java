package com.matos.capello.repository;

import com.matos.capello.model.Opportunity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class OpportunityRepositoryTest {

    @Autowired
    private OpportunityRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testSearchExistentOpportunityByKey() {
        // given
        String key = "OPP-0001";
        Opportunity opportunity = new Opportunity(
                key,
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments"
        );
        this.underTest.save(opportunity);

        // when
        Opportunity expected = this.underTest.findOpportunityByKey(key);

        //then
        assertThat(expected).isEqualTo(opportunity);
    }

    @Test
    void testSearchExistentOpportunityToString() {
        // given
        String key = "OPP-0001";
        Opportunity opportunity = new Opportunity(
                key,
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments"
        );
        this.underTest.save(opportunity);

        // when
        Opportunity expected = this.underTest.findOpportunityByKey(key);

        //then
        assertThat(expected.toString()).isEqualTo(opportunity.toString());
    }

    @Test
    void testSearchNonExistentOpportunityByKey() {
        // given
        String key = "OPP-0001";
        Opportunity opportunity = new Opportunity(
                key,
                "title",
                "description",
                "progress",
                "suggestedBy",
                "impacted_areas",
                "priority",
                null,
                "comments"
        );

        // when
        Opportunity expected = this.underTest.findOpportunityByKey(key);

        //then
        assertThat(expected).isNull();
    }

}