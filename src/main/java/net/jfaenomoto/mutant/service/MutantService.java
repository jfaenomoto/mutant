package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.domain.MappedDNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private MutantAnalysisService mutantAnalysisService;
    private DNAService dnaService;
    private CacheService cacheService;

    @Autowired
    public MutantService(MutantAnalysisService mutantAnalysisService,
                         DNAService dnaService,
                         CacheService cacheService) {
        if (mutantAnalysisService == null) {
            throw new IllegalArgumentException("mutantAnalysisService can't be null");
        }
        if (dnaService == null) {
            throw new IllegalArgumentException("dnaService can't be null");
        }
        if (cacheService == null) {
            throw new IllegalArgumentException("cacheService can't be null");
        }
        this.mutantAnalysisService = mutantAnalysisService;
        this.dnaService = dnaService;
        this.cacheService = cacheService;
    }

    public boolean isMutant(String[] dna) {
        MappedDNA mappedDNA = this.dnaService.findBy(dna);
        if (mappedDNA != null) {
            return mappedDNA.isMutant();
        }
        boolean isMutant = this.mutantAnalysisService.isMutant(dna);
        if (isMutant) {
            this.cacheService.addMutant();
        } else {
            this.cacheService.addHuman();
        }
        this.dnaService.save(dna, isMutant);
        return isMutant;
    }

}
