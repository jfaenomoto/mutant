package net.jfaenomoto.mutant.controller;

import net.jfaenomoto.mutant.model.DNARequest;
import net.jfaenomoto.mutant.model.exception.MutantException;
import net.jfaenomoto.mutant.service.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private MutantService mutantService;

    @Autowired
    public MutantController(MutantService mutantService) {
        if (mutantService == null) {
            throw new IllegalArgumentException("mutantService can't be null");
        }
        this.mutantService = mutantService;
    }

    @PostMapping(consumes = "application/json")
    public void checkMutant(@RequestBody DNARequest dna) throws MutantException {
        if (dna == null) {
            throw new IllegalArgumentException("dna can't be null");
        }
        if (this.mutantService.isMutant(dna.getDna())) {
            throw new MutantException();
        }
    }

}
