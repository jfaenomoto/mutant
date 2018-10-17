package net.jfaenomoto.mutant.controller;

import net.jfaenomoto.mutant.model.DNARequest;
import net.jfaenomoto.mutant.model.exception.MutantException;
import net.jfaenomoto.mutant.service.MutantAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private MutantAnalysisService mutantAnalysisService;

    @Autowired
    public MutantController(MutantAnalysisService mutantAnalysisService) {
        if (mutantAnalysisService == null) {
            throw new IllegalArgumentException("mutantAnalysisService can't be null");
        }
        this.mutantAnalysisService = mutantAnalysisService;
    }

    @PostMapping(consumes = "application/json")
    public void checkMutant(@RequestBody DNARequest dna) throws MutantException {
        if (this.mutantAnalysisService.isMutant(dna.getDna())) {
            throw new MutantException();
        }
    }

}
