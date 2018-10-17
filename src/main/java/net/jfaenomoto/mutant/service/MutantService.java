package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.domain.MappedDNA;
import net.jfaenomoto.mutant.repository.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {

    private MutantAnalysisService mutantAnalysisService;
    private DNARepository dnaRepository;
    private CacheService cacheService;

    @Autowired
    public MutantService(MutantAnalysisService mutantAnalysisService,
                         DNARepository dnaRepository,
                         CacheService cacheService) {
        if (mutantAnalysisService == null) {
            throw new IllegalArgumentException("mutantAnalysisService can't be null");
        }
        if (dnaRepository == null) {
            throw new IllegalArgumentException("dnaRepository can't be null");
        }
        if (cacheService == null) {
            throw new IllegalArgumentException("cacheService can't be null");
        }
        this.mutantAnalysisService = mutantAnalysisService;
        this.dnaRepository = dnaRepository;
        this.cacheService = cacheService;
    }

    public boolean isMutant(String[] dna) {
        MappedDNA mappedDNA = this.dnaRepository.findBy(dna);
        if (mappedDNA != null) {
            return mappedDNA.isMutant();
        }
        boolean isMutant = this.mutantAnalysisService.isMutant(dna);
        if (isMutant) {
            this.cacheService.addMutant();
        } else {
            this.cacheService.addHuman();
        }
        this.dnaRepository.save(dna, isMutant);
        return isMutant;
    }

}
