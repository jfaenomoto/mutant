package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.repository.MappedDNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * This service acts as a cache for processed data. Note: it's implemented as a singleton service. For clustered
 * applications, this should be implemented as an interface to a Redis or Memcached.
 *
 * @author jfaenomoto
 */
@Service
public class CacheService {

    private MappedDNARepository mappedDNARepository;

    private long humans;

    private long mutants;

    @Autowired
    public CacheService(MappedDNARepository mappedDNARepository) {
        if (mappedDNARepository == null) {
            throw new IllegalArgumentException("mappedDNARepository can't be null");
        }
        this.mappedDNARepository = mappedDNARepository;
    }

    @PostConstruct
    public void loadCache() {
        this.humans = this.mappedDNARepository.countByIsMutant(false);
        this.mutants = this.mappedDNARepository.countByIsMutant(true);
    }

    public long getHumans() {
        return this.humans;
    }

    public void addHuman() {
        this.humans++;
    }

    public long getMutants() {
        return this.mutants;
    }

    public void addMutant() {
        this.mutants++;
    }

}
