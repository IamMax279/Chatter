package com.max420.Scrapr_server.controllers;

import com.max420.Scrapr_server.services.SeleniumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/selenium")
public class SeleniumController {
    private final SeleniumService seleniumService;

    public SeleniumController(SeleniumService seleniumService) {
        this.seleniumService = seleniumService;
    }

    @GetMapping("/fetch-some-data")
    public ResponseEntity<?> fetchSomeData() {
        return ResponseEntity
                .ok(Map.of("found titles", seleniumService.scrapeSomeData()));
    }
}
