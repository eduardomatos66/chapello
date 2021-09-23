package com.matos.capello.repository;

import com.matos.capello.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository
        extends JpaRepository<Professor, Long> {


    @Query("SELECT p FROM Professor p WHERE p.name = ?1")
    Professor findProfessorByName(String name);

    @Query("SELECT p FROM Professor p WHERE p.email = ?1")
    Professor findProfessorByEmail(String email);

//    @Query("SELECT p FROM Professor p WHERE p.institute_id = ?1")
//    Professor findProfessorByInstituteId(String instituteId);
}
