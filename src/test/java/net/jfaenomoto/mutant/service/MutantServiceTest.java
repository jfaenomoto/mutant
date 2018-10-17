package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.domain.MappedDNA;
import net.jfaenomoto.mutant.repository.DNARepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MutantServiceTest {

    private MutantService service;

    private MutantAnalysisService mutantAnalysisService;
    private DNARepository dnaRepository;
    private CacheService cacheService;

    @Test
    public void isMutantByCache() {
        MappedDNA mappedDNA = new MappedDNA();
        mappedDNA.setMutant(true);
        when(this.dnaRepository.findBy(any())).thenReturn(mappedDNA);

        assertTrue(this.service.isMutant(new String[]{}));

        verify(this.mutantAnalysisService, times(0)).isMutant(any());
        verify(this.dnaRepository, times(0)).save(any(), anyBoolean());
    }

    @Test
    public void isMutantNoCache() {
        when(this.dnaRepository.findBy(any())).thenReturn(null);
        when(this.mutantAnalysisService.isMutant(any())).thenReturn(true);

        assertTrue(this.service.isMutant(new String[]{}));

        verify(this.cacheService, times(1)).addMutant();
        verify(this.dnaRepository, times(1)).save(any(), eq(true));
    }

    @Test
    public void isHumanNoCache() {
        when(this.dnaRepository.findBy(any())).thenReturn(null);
        when(this.mutantAnalysisService.isMutant(any())).thenReturn(false);

        assertFalse(this.service.isMutant(new String[]{}));

        verify(this.cacheService, times(1)).addHuman();
        verify(this.dnaRepository, times(1)).save(any(), eq(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMutantAnalysisServiceIsNull() {
        new MutantService(null, this.dnaRepository, this.cacheService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfDNARepositoryIsNull() {
        new MutantService(this.mutantAnalysisService, null, this.cacheService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfCacheServiceIsNull() {
        new MutantService(this.mutantAnalysisService, this.dnaRepository, null);
    }

    @Before
    public void setup() {
        this.mutantAnalysisService = mock(MutantAnalysisService.class);
        this.dnaRepository = mock(DNARepository.class);
        this.cacheService = mock(CacheService.class);

        this.service = new MutantService(this.mutantAnalysisService, this.dnaRepository, this.cacheService);
    }

}