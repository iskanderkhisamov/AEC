package ru.kstu.aec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kstu.aec.models.Scholar;
import ru.kstu.aec.services.StatsGiver;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class StatisticsAPIController {

    private final StatsGiver statsGiver;

    @Autowired
    public StatisticsAPIController(StatsGiver statsGiver) {
        this.statsGiver = statsGiver;
    }

    @GetMapping("/statistics/{name}")
    public Scholar statistics(@PathVariable String name) {
        return statsGiver.findByName(name);
    }

}
