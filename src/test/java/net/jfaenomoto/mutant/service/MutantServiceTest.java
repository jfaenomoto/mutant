package net.jfaenomoto.mutant.service;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MutantServiceTest {

    private MutantService mutantService;

    @Test
    public void isMutantHorizontal() {
        String[] dna = {
                "TAGC",
                "TCGA",
                "CCAT",
                "GGGG"
        };

        assertTrue(this.mutantService.isMutant(dna));
    }

    @Test
    public void isMutantVertical() {
        String[] dna = {
                "GTAA",
                "CTGC",
                "ATGC",
                "TTAA"
        };

        assertTrue(this.mutantService.isMutant(dna));
    }

    @Test
    public void isMutantDiagonal() {
        String[] dna = {
                "GTTA",
                "CGAT",
                "TTGA",
                "CTAG"
        };

        assertTrue(this.mutantService.isMutant(dna));
    }

    @Test
    public void isMutantOpposingDiagonal() {
        String[] dna = {
                "TAGC",
                "TACG",
                "GCTA",
                "CCAG"
        };

        assertTrue(this.mutantService.isMutant(dna));
    }

    @Test
    public void isNotMutant() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };

        assertFalse(this.mutantService.isMutant(dna));
    }

    @Test
    public void isNotMutantOnSmallerThan4x4() {
        String[] dna = {
                "TAG",
                "CGA",
                "GGT"
        };

        assertFalse(this.mutantService.isMutant(dna));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfDNAIsNullOnMutantCheck() {
        this.mutantService.isMutant(null);
    }

    @Test(expected = IllegalStateException.class)
    public void throwExceptionIfDNAIsNotNxNOnMutantCheck() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT"
        };

        this.mutantService.isMutant(dna);
    }

    @Before
    public void setup() {
        this.mutantService = new MutantService();
    }
}