package com.matos.capello.repository;

import com.matos.capello.model.Institute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository
        extends JpaRepository<Institute, Long> {
}
