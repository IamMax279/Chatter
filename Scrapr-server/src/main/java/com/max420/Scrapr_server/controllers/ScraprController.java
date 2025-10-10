package com.max420.Scrapr_server.controllers;

import com.max420.Scrapr_server.services.JsoupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scrapr")
public class ScraprController {
    private final JsoupService jsoupService;

    public ScraprController(JsoupService jsoupService) {
        this.jsoupService = jsoupService;
    }

    @GetMapping("/fetch-some-data")
    public ResponseEntity<?> fetchSomeData() {
        try {
            List<String> headlines = jsoupService.fetchSomeData();
            return ResponseEntity
                    .ok()
                    .body(Map.of("DOM headlines", headlines));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
