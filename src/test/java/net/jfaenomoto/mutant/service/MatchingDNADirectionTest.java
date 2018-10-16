package net.jfaenomoto.mutant.service;

import org.junit.Before;
import org.junit.Test;

import static net.jfaenomoto.mutant.service.Direction.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchingDNADirectionTest {

    private MatchingDNADirection direction;

    @Test
    public void isMutantVertical() {
        assertTrue(this.direction.isMutant(0, 4, VERTICAL));
    }

    @Test
    public void isMutantDiagonal() {
        assertTrue(this.direction.isMutant(0, 0, DIAGONAL));
    }

    @Test
    public void isMutantHorizontal() {
        assertTrue(this.direction.isMutant(4, 0, HORIZONTAL));
    }

    @Test
    public void isNotMutantVertical() {
        assertFalse(this.direction.isMutant(0, 3, VERTICAL));
    }

    @Test
    public void isNotMutantDiagonal() {
        assertFalse(this.direction.isMutant(2, 0, DIAGONAL));
    }

    @Test
    public void isNotMutantHorizontal() {
        assertFalse(this.direction.isMutant(5, 0, HORIZONTAL));
    }

    @Test
    public void isNotMutantOutOfBounds() {
        assertFalse(this.direction.isMutant(10, 0, HORIZONTAL));
        assertFalse(this.direction.isMutant(2, 15, VERTICAL));
        assertFalse(this.direction.isMutant(-5, 4, OPPOSING_DIAGONAL));
        assertFalse(this.direction.isMutant(3, -21, DIAGONAL));
    }

    @Test
    public void isMutantRestartSequence() {
        String[] dna = {"TACGGGGT"};
        this.direction = new MatchingDNADirection(dna);

        assertTrue(this.direction.isMutant(0, 0, HORIZONTAL));
    }

    @Test
    public void isNotMutantNonContinuousSequence() {
        String[] dna = {"CATAAAGACA"};
        this.direction = new MatchingDNADirection(dna);

        assertFalse(this.direction.isMutant(0, 0, HORIZONTAL));
    }

    @Before
    public void setup() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        this.direction = new MatchingDNADirection(dna);
    }

}