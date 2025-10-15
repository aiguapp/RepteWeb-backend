package com.aiguaapp.aiguaapp.controller;

import com.aiguaapp.aiguaapp.service.FileDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/anomalies")
public class AnomaliesController {

    private final FileDataService fileDataService;

    public AnomaliesController(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @GetMapping("/")
    public ResponseEntity<String> get_anomalies() {
        return fileDataService.readJsonFile("anomalies.json");
    }
}