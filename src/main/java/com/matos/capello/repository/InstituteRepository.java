package com.matos.capello.repository;

import com.matos.capello.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository
        extends JpaRepository<Institute, Long> {

    @Query("SELECT i FROM Institute i WHERE i.shortName = ?1")
    Institute findInstituteByShortName(String shortName);

    @Query("SELECT i FROM Institute i WHERE i.longName = ?1")
    Institute findInstituteByLongName(String longName);
}
