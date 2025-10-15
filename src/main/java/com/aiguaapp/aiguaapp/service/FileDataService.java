package com.aiguaapp.aiguaapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

@Service
public class FileDataService {

    @Value("${dataJson.path:src/main/resources/dataJson}")
    private String dataJsonPath;

    public ResponseEntity<String> readJsonFile(String fileName) {
        Path filePath = Path.of(dataJsonPath, fileName);
        try {
            String jsonString = Files.readString(filePath);
            return ResponseEntity.ok(jsonString);
        } catch (NoSuchFileException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("File not found: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading file: " + e.getMessage());
        }
    }
}