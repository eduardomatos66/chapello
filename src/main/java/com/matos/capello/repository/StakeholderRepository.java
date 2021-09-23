package com.matos.capello.repository;

import com.matos.capello.model.Stakeholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StakeholderRepository
        extends JpaRepository<Stakeholder, Long> {
}
