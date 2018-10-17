package net.jfaenomoto.mutant.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusResponseTest {

    @Test
    public void getRatio() {
        StatusResponse response = StatusResponse.response(25, 16);

        assertEquals(0.609, response.getRatio(), 0.001);
    }

    @Test
    public void getRatioWhenMutantsAndHumansAreZero() {
        StatusResponse response = StatusResponse.response(0, 0);

        assertEquals(0.0, response.getRatio(), 0.001);
    }

}