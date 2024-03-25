package org.itstep.api.controllers;


import lombok.RequiredArgsConstructor;
import org.itstep.api.drivers.storages.DriverEnum;
import org.itstep.api.services.StorageService;
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

    private final StorageService storageService;

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        System.out.println("fileName: " + fileName);

//        storageService.disk(DriverEnum.Local).put("files", fileName, file);
//        storageService.disk(DriverEnum.MinIo).put("files", fileName, file);

        String filePath = storageService.put("files", fileName, file);

        return ResponseEntity.ok(filePath);
    }


}
