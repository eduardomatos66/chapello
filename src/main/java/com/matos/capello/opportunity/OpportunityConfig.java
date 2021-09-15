package com.matos.capello.opportunity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class OpportunityConfig {

    @Bean
    CommandLineRunner commandLineRunner(OpportunityRepository repository) {
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
            repository.saveAll(List.of(o1, o2));

        };
    }

}
