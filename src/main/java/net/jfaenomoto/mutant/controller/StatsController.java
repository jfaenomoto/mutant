package net.jfaenomoto.mutant.controller;

import net.jfaenomoto.mutant.model.StatusResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    @GetMapping(consumes = "application/json")
    public StatusResponse getStats() {
        return new StatusResponse();
    }

}
