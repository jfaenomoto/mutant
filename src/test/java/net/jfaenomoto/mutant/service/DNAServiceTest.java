package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.domain.MappedDNA;
import net.jfaenomoto.mutant.repository.MappedDNARepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DNAServiceTest {

    private DNAService dnaService;

    private MappedDNARepository mappedDNARepository;

    @Test
    public void findByDNAArray() {
        String[] dna = new String[]{"ACT", "CCT"};
        when(this.mappedDNARepository.findByHash("9c815f0c5c0899860d03c265bff7be02ba6ab74a1794e1312d12ccef6a2217d9"))
                .thenReturn(new MappedDNA());

        assertNotNull(this.dnaService.findBy(dna));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfDNAIsNullOnFindBy() {
        this.dnaService.findBy(null);
    }

    @Test
    public void save() {
        String[] dna = new String[]{"CCTG", "AAGC"};
        when(this.mappedDNARepository.save(any()))
                .thenAnswer((Answer<MappedDNA>) invocation -> {
                    Object[] args = invocation.getArguments();
                    return (MappedDNA) args[0];
                });

        MappedDNA mappedDNA = this.dnaService.save(dna, false);

        assertEquals("CCTG\nAAGC", mappedDNA.getDna());
        assertEquals("5052eccfd94f388588c0d792ca2f31278c56fa0ba54d8427d6854c4f5dc4ad32", mappedDNA.getHash());
        assertFalse(mappedDNA.isMutant());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfDNAIsNullOnSave() {
        this.dnaService.save(null, true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfRepositoryIsNull() {
        new DNAService(null);
    }

    @Before
    public void setup() {
        this.mappedDNARepository = mock(MappedDNARepository.class);
        this.dnaService = new DNAService(this.mappedDNARepository);
    }
}