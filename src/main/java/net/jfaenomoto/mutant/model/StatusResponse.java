package net.jfaenomoto.mutant.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusResponse {

    private long mutants;

    private long humans;

    public static StatusResponse response(long mutants, long humans) {
        StatusResponse response = new StatusResponse();
        response.mutants = mutants;
        response.humans = humans;
        return response;
    }

    @JsonProperty("count_mutant_dna")
    public long getMutants() {
        return mutants;
    }

    public void setMutants(int mutants) {
        this.mutants = mutants;
    }

    @JsonProperty("count_human_dna")
    public long getHumans() {
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
        return (double) this.mutants / (this.humans + this.mutants);
    }

}
