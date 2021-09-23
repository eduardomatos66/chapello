package com.matos.capello.business;

import com.matos.capello.exception.InstituteNotExistentException;
import com.matos.capello.model.Institute;
import com.matos.capello.repository.InstituteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class InstituteService {
    private final InstituteRepository instituteRepository;

    @GetMapping
    public List<Institute> getInstitutes() {
        return this.instituteRepository.findAll();
    }

    public Institute getInstituteById(Long instituteId) {
        Optional<Institute> optionalInstitute = this.instituteRepository.findById(instituteId);

        if (optionalInstitute.isEmpty()) {
            throw new InstituteNotExistentException(
                    String.format("There is no institute for id: %s", instituteId));
        }

        return optionalInstitute.get();
    }

    public Institute getInstituteByShortName(String shortName) {
        Institute institute = this.instituteRepository.findInstituteByShortName(shortName);

        if (institute == null) {
            throw new InstituteNotExistentException(
                    String.format("There is no Institute for short name: %s", shortName));
        }

        return institute;
    }

    public Institute getInstituteByLongName(String longName) {
        Institute institute = this.instituteRepository.findInstituteByLongName(longName);

        if (institute == null) {
            throw new InstituteNotExistentException(
                    String.format("There is no Institute for long name: %s", longName));
        }

        return institute;
    }

}
