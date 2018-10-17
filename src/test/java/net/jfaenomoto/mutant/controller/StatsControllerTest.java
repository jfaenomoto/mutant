package net.jfaenomoto.mutant.controller;

import net.jfaenomoto.mutant.model.StatusResponse;
import net.jfaenomoto.mutant.service.CacheService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatsControllerTest {

    private StatsController controller;

    private CacheService cacheService;

    @Test
    public void getStats() {
        when(this.cacheService.getHumans()).thenReturn(56);
        when(this.cacheService.getMutants()).thenReturn(32);

        StatusResponse stats = this.controller.getStats();

        assertEquals(56, stats.getHumans());
        assertEquals(32, stats.getMutants());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfCacheServiceIsNull() {
        new StatsController(null);
    }

    @Before
    public void setup() {
        this.cacheService = mock(CacheService.class);
        this.controller = new StatsController(this.cacheService);
    }

}