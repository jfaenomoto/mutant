package net.jfaenomoto.mutant.domain;

import javax.persistence.*;

@Entity
@Table(name = "mapped_dna")
public class MappedDNA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "raw_dna")
    private String dna;

    @Column(name = "hash")
    private String hash;

    @Column(name = "is_mutant")
    private boolean isMutant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public boolean isMutant() {
        return isMutant;
    }

    public void setMutant(boolean mutant) {
        isMutant = mutant;
    }

}
