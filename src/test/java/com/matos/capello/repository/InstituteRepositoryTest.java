package com.matos.capello.repository;

import com.matos.capello.model.Institute;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class InstituteRepositoryTest {

    @Autowired
    private InstituteRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void testSearchExistentInstituteByShortName() {
        // given
        String shortName = "inst1";
        Institute institute = new Institute(shortName, "institute 1");
        this.underTest.save(institute);

        // when
        Institute expected = this.underTest.findInstituteByShortName(shortName);

        //then
        assertThat(expected).isEqualTo(institute);
    }

    @Test
    void testSearchNonExistentInstituteByShortName() {
        // given
        String shortName = "inst1";
        Institute institute = new Institute(shortName, "institute 1");
        this.underTest.save(institute);

        // when
        Institute expected = this.underTest.findInstituteByShortName("inst2");

        //then
        assertThat(expected).isNull();
    }

    @Test
    void testSearchExistentInstituteByLongName() {
        // given
        String longName = "institute 1";
        Institute institute = new Institute("inst1", longName);
        this.underTest.save(institute);

        // when
        Institute expected = this.underTest.findInstituteByLongName(longName);

        //then
        assertThat(expected.toString()).isEqualTo(institute.toString());
    }

    @Test
    void testSearchNonExistentInstituteByLongName() {
        // given
        String longName = "institute 1";
        Institute institute = new Institute("inst1", longName);
        this.underTest.save(institute);

        // when
        Institute expected = this.underTest.findInstituteByLongName("institute 2");

        //then
        assertThat(expected).isNull();
    }
}