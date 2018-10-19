package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.domain.MappedDNA;
import net.jfaenomoto.mutant.repository.MappedDNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

import static com.google.common.hash.Hashing.sha256;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.stream.Collectors.joining;

@Repository
public class DNAService {

    private MappedDNARepository mappedDNARepository;

    @Autowired
    public DNAService(MappedDNARepository mappedDNARepository) {
        if (mappedDNARepository == null) {
            throw new IllegalArgumentException("mappedDNARepository can't be null");
        }
        this.mappedDNARepository = mappedDNARepository;
    }

    public MappedDNA findBy(String[] dna) {
        if (dna == null) {
            throw new IllegalArgumentException("dna can't be null");
        }
        return this.mappedDNARepository
                .findByHash(sha256().hashString(this.dnaFromArray(dna), UTF_8).toString());
    }

    public MappedDNA save(String[] dna, boolean isMutant) {
        if (dna == null) {
            throw new IllegalArgumentException("dna can't be null");
        }
        MappedDNA mappedDNA = new MappedDNA();
        mappedDNA.setDna(this.dnaFromArray(dna));
        mappedDNA.setHash(sha256().hashString(this.dnaFromArray(dna), UTF_8).toString());
        mappedDNA.setMutant(isMutant);
        return this.mappedDNARepository.save(mappedDNA);
    }

    private String dnaFromArray(String[] dna) {
        return Stream.of(dna)
                .collect(joining("\n"));
    }

}
