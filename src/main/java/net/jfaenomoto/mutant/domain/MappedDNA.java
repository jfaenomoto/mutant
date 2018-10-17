package net.jfaenomoto.mutant.domain;

public class MappedDNA {

    private String dna;

    private boolean isMutant;

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }

}
