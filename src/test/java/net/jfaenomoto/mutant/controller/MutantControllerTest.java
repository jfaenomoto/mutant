package net.jfaenomoto.mutant.controller;

import net.jfaenomoto.mutant.model.DNARequest;
import net.jfaenomoto.mutant.model.exception.MutantException;
import net.jfaenomoto.mutant.service.MutantService;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MutantControllerTest {

    private MutantController controller;

    private MutantService mutantService;

    @Test(expected = MutantException.class)
    public void checkMutant() throws MutantException {
        DNARequest request = new DNARequest();
        when(this.mutantService.isMutant(any())).thenReturn(true);

        this.controller.checkMutant(request);
    }

    @Test
    public void checkNotMutant() throws MutantException {
        DNARequest request = new DNARequest();
        when(this.mutantService.isMutant(any())).thenReturn(false);

        this.controller.checkMutant(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfDNAIsNullOnMutantCheck() throws MutantException {
        this.controller.checkMutant(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwExceptionIfMutantServiceIsNull() {
        new MutantController(null);
    }

    @Before
    public void setup() {
        this.mutantService = mock(MutantService.class);
        this.controller = new MutantController(this.mutantService);
    }
}