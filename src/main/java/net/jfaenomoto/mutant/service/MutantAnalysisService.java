package net.jfaenomoto.mutant.service;

import org.springframework.stereotype.Service;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static net.jfaenomoto.mutant.service.Direction.*;

/**
 * Service that looks for mutants
 *
 * @author jfaenomoto
 */
@Service
public class MutantAnalysisService {

    /**
     * Given a DNA table, represented as an array of Strings, check if it's mutant or not
     *
     * @param dna dna table
     * @return true if mutant, false otherwise
     */
    public boolean isMutant(String[] dna) {
        if (dna == null) {
            throw new IllegalArgumentException("dna can't be null");
        }

        for (String singleDna : dna) {
            if (singleDna.length() != dna.length) {
                throw new IllegalStateException("dna sequence isn't NxN");
            }
        }

        if (dna.length < 4) {
            return false;
        }

        MatchingDNADirection direction = new MatchingDNADirection(dna);
        for (int i = 0; i < dna.length; i++) {
            if (direction.isMutant(0, i, VERTICAL)) {
                return true;
            }
            if (direction.isMutant(i, 0, HORIZONTAL)) {
                return true;
            }
        }

        for (int i = 0; i < 2 * dna.length - 7; i++) {
            if (direction.isMutant(max(dna.length - i - 4, 0), max(i - dna.length + 4, 0), DIAGONAL)) {
                return true;
            }
            if (direction.isMutant(max(i - dna.length + 4, 0), min(i + 3, dna.length), OPPOSING_DIAGONAL)) {
                return true;
            }
        }

        return false;
    }

}


