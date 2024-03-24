package org.itstep.api.services;

import org.itstep.api.drivers.storages.StorageDriverInterface;
import org.itstep.api.drivers.storages.StorageDriverLocal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {

    StorageDriverInterface driver;

    public StorageService() {
        System.out.println(" Storage Service Created");
        driver = new StorageDriverLocal();
    }

    public boolean put (String bucketName, String fileName, MultipartFile file) {
        return driver.put(bucketName,fileName,file);
    }
}
