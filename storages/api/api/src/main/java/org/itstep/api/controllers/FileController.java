package org.itstep.api.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileController {

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        System.out.println("fileName: " + fileName);

        return ResponseEntity.ok(fileName);
    }


}
