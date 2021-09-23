package com.matos.capello.configs;

import com.matos.capello.model.Institute;
import com.matos.capello.model.Opportunity;
import com.matos.capello.repository.InstituteRepository;
import com.matos.capello.repository.OpportunityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(
            OpportunityRepository opportunityRepository,
            InstituteRepository instituteRepository) {

        return args -> {
            Opportunity o1 = new Opportunity(
                    "OPP-0001",
                    "Title",
                    "Description",
//                        List.of("ai", "test"),
                    "ON GOING",
                    "ematos",
                    "smoke test",
//                        List.of("ematos"),
                    "high",
//                        List.of(""),
                    LocalDate.of(2020, Month.JUNE, 1),
                    ""
            );

            Opportunity o2 = new Opportunity(
                    "OPP-0002",
                    "Title2",
                    "Description2",
//                        List.of("test"),
                    "DONE",
                    "raphaelf",
                    "research test",
//                        List.of("raphaelf"),
                    "high",
//                        List.of(""),
                    LocalDate.of(2020, Month.AUGUST, 21),
                    ""
            );
            Institute i1 = new Institute(
                    "CIn/UFPE",
                    "Centro de Informática da Universidade Federal de Pernambuco"
            );

            Institute i2 = new Institute(
                    "INDT",
                    "Instituto de Tecnologia"
            );

            opportunityRepository.saveAll(List.of(o1, o2));
            instituteRepository.saveAll(List.of(i1, i2));
        };
    }

}
