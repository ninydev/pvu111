package org.itstep.api.services;

import org.itstep.api.drivers.storages.StorageDriverInterface;
import org.itstep.api.drivers.storages.StorageDriverLocal;
import org.itstep.api.drivers.storages.StorageDriverMinIo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {


    private String minioUrl = "http://localhost:9000";


    private String minioUsername = "user";


    private String minioPassword = "password";

    StorageDriverInterface driver;

    public StorageService() {
        System.out.println(" Storage Service Created");
        // driver = new StorageDriverLocal();
        driver = new StorageDriverMinIo(minioUrl, minioUsername, minioPassword );
    }

    public String put (String bucketName, String fileName, MultipartFile file) {
        return driver.put(bucketName,fileName,file);
    }
}
