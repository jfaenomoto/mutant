package net.jfaenomoto.mutant.service;

import net.jfaenomoto.mutant.repository.MappedDNARepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CacheServiceTest {

    private CacheService cacheService;

    private MappedDNARepository mappedDNARepository;

    @Test
    public void loadCache() {
        when(this.mappedDNARepository.countByIsMutant(false)).thenReturn(224L);
        when(this.mappedDNARepository.countByIsMutant(true)).thenReturn(353L);

        this.cacheService.loadCache();

        assertEquals(224L, this.cacheService.getHumans());
        assertEquals(353L, this.cacheService.getMutants());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfRepositoryIsNull() {
        new CacheService(null);
    }

    @Before
    public void setup() {
        this.mappedDNARepository = mock(MappedDNARepository.class);
        this.cacheService = new CacheService(this.mappedDNARepository);
    }
}