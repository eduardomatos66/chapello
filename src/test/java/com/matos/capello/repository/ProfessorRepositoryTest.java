package com.matos.capello.repository;

import com.matos.capello.CapelloApplicationTests;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorRepositoryTest extends CapelloApplicationTests {

    @Autowired
    private ProfessorRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
}