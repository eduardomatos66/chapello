package com.matos.capello.opportunity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    @Autowired
    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }

    @GetMapping
    public List<Opportunity> getOpportunities() {
        return this.opportunityRepository.findAll();
//        return List.of(
//                new Opportunity(
//                        "OPP-0001",
//                        "Title",
//                        "Description",
////                        List.of("ai", "test"),
//                        "ON GOING",
//                        "ematos",
//                        "smoke test",
////                        List.of("ematos"),
//                        "high",
////                        List.of(""),
//                        LocalDate.now(),
//                        ""
//                ),
//                new Opportunity(
//                        "OPP-0002",
//                        "Title2",
//                        "Description2",
////                        List.of("test"),
//                        "DONE",
//                        "raphaelf",
//                        "research test",
////                        List.of("raphaelf"),
//                        "high",
////                        List.of(""),
//                        LocalDate.now(),
//                        ""
//                )
//        );
    }

}
