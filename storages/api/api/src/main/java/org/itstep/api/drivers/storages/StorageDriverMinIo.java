package org.itstep.api.drivers.storages;

import org.springframework.web.multipart.MultipartFile;

public class StorageDriverMinIo implements StorageDriverInterface
{
    @Override
    public boolean put(String bucketName, String fileName, MultipartFile file) {
        return false;
    }
}
