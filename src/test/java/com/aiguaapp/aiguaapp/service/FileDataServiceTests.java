package com.aiguaapp.aiguaapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

class FileDataServiceTests {

    private FileDataService fileDataService;
    private Path testDir;
    private Path testFile;

    @BeforeEach
    void setup() throws IOException, NoSuchFieldException, IllegalAccessException {
        testDir = Path.of("src/test/resources/fakeDataJson");
        testFile = testDir.resolve("example.json");
        Files.createDirectories(testDir);

        Files.writeString(testFile, "{\"msg\":\"ok\"}");

        fileDataService = new FileDataService();

        var field = FileDataService.class.getDeclaredField("dataJsonPath");
        field.setAccessible(true);
        field.set(fileDataService, testDir.toString());
    }

    @Test
    void shouldReturnFileContents() {
        ResponseEntity<String> response = fileDataService.readJsonFile("example.json");

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(response.getBody()).contains("ok");
    }

    @Test
    void shouldReturn404IfFileMissing() {
        ResponseEntity<String> response = fileDataService.readJsonFile("missing.json");

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(response.getBody()).contains("File not found");
    }

    @Test
    void shouldReturn500IfUnreadableFile() throws IOException {
        Files.writeString(testFile, "{\"msg\":\"bad\"}");
        testFile.toFile().setReadable(false);

        ResponseEntity<String> response = fileDataService.readJsonFile("example.json");

        assertThat(response.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).contains("Error reading file");

        testFile.toFile().setReadable(true);
    }
}