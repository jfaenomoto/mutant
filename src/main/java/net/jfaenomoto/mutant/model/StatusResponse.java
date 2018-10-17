package net.jfaenomoto.mutant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusResponse {

    private int mutants;

    private int humans;

    public static StatusResponse response(int mutants, int humans) {
        StatusResponse response = new StatusResponse();
        response.mutants = mutants;
        response.humans = humans;
        return response;
    }

    @JsonProperty("count_mutant_dna")
    public int getMutants() {
        return mutants;
    }

    public void setMutants(int mutants) {
        this.mutants = mutants;
    }

    @JsonProperty("count_human_dna")
    public int getHumans() {
        return humans;
    }

    public void setHumans(int humans) {
        this.humans = humans;
    }

    @JsonProperty("ratio")
    public double getRatio() {
        if (this.humans + this.mutants == 0) {
            return 0.0;
        }
        return this.mutants / (this.humans + this.mutants);
    }

}
