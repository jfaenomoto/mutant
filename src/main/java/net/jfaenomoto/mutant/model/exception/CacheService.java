package net.jfaenomoto.mutant.model.exception;

import org.springframework.stereotype.Service;

/**
 * This service acts as a cache for processed data. Note: it's implemented as a singleton service. For clustered
 * applications, this should be implemented as an interface to a Redis or Memcached.
 *
 * @author jfaenomoto
 */
@Service
public class CacheService {

    private int humans;

    private int mutants;

    public int getHumans() {
        return this.humans;
    }

    public int getMutants() {
        return this.mutants;
    }

}
