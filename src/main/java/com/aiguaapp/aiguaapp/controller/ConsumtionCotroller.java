package com.aiguaapp.aiguaapp.controller;

import com.aiguaapp.aiguaapp.service.FileDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/consumption")
public class ConsumtionCotroller {

    private final FileDataService fileDataService;

    public ConsumtionCotroller(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @GetMapping("/")
    public ResponseEntity<String> get_consumption() {
        return fileDataService.readJsonFile("consumption.json");
    }

    @GetMapping("/summary")
    public ResponseEntity<String> get_summary() {
        return fileDataService.readJsonFile("summary.json");
    }
}