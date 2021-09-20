package com.matos.capello.repository;

import com.matos.capello.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OpportunityRepository
        extends JpaRepository<Opportunity, Long> {

    @Query("SELECT s FROM Opportunity s WHERE s.key = ?1")
    Opportunity findOpportunityByKey(String key);

}
