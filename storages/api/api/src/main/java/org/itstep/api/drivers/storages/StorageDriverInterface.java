package org.itstep.api.drivers.storages;

import org.springframework.web.multipart.MultipartFile;

public interface StorageDriverInterface {
    public boolean put (String bucketName, String fileName, MultipartFile file);
}
