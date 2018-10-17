package net.jfaenomoto.mutant.controller;

import net.jfaenomoto.mutant.model.StatusResponse;
import net.jfaenomoto.mutant.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private CacheService cacheService;

    @Autowired
    public StatsController(CacheService cacheService) {
        if (cacheService == null) {
            throw new IllegalArgumentException("cacheService can't be null");
        }
        this.cacheService = cacheService;
    }

    @GetMapping(consumes = "application/json")
    public StatusResponse getStats() {
        return StatusResponse.response(
                this.cacheService.getMutants(),
                this.cacheService.getHumans());
    }

}
