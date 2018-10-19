package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.domain.MappedDNA;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MutantServiceTest {

    private MutantService service;

    private MutantAnalysisService mutantAnalysisService;
    private DNAService dnaService;
    private CacheService cacheService;

    @Test
    public void isMutantByCache() {
        MappedDNA mappedDNA = new MappedDNA();
        mappedDNA.setMutant(true);
        when(this.dnaService.findBy(any())).thenReturn(mappedDNA);

        assertTrue(this.service.isMutant(new String[]{}));

        verify(this.mutantAnalysisService, times(0)).isMutant(any());
        verify(this.dnaService, times(0)).save(any(), anyBoolean());
    }

    @Test
    public void isMutantNoCache() {
        when(this.dnaService.findBy(any())).thenReturn(null);
        when(this.mutantAnalysisService.isMutant(any())).thenReturn(true);

        assertTrue(this.service.isMutant(new String[]{}));

        verify(this.cacheService, times(1)).addMutant();
        verify(this.dnaService, times(1)).save(any(), eq(true));
    }

    @Test
    public void isHumanNoCache() {
        when(this.dnaService.findBy(any())).thenReturn(null);
        when(this.mutantAnalysisService.isMutant(any())).thenReturn(false);

        assertFalse(this.service.isMutant(new String[]{}));

        verify(this.cacheService, times(1)).addHuman();
        verify(this.dnaService, times(1)).save(any(), eq(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMutantAnalysisServiceIsNull() {
        new MutantService(null, this.dnaService, this.cacheService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfDNAServiceIsNull() {
        new MutantService(this.mutantAnalysisService, null, this.cacheService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfCacheServiceIsNull() {
        new MutantService(this.mutantAnalysisService, this.dnaService, null);
    }

    @Before
    public void setup() {
        this.mutantAnalysisService = mock(MutantAnalysisService.class);
        this.dnaService = mock(DNAService.class);
        this.cacheService = mock(CacheService.class);

        this.service = new MutantService(this.mutantAnalysisService, this.dnaService, this.cacheService);
    }

}