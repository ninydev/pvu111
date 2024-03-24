package org.itstep.api.drivers.storages;

import org.springframework.web.multipart.MultipartFile;

public class StorageDriverWriter implements  StorageDriverInterface
{
    @Override
    public String put(String bucketName, String fileName, MultipartFile file) {
        System.out.println(" Я писец - моя задача - переписать то что мне дали " +
                "на пергамент - причем выбить с рисунками и тд");

        return null;
    }
}
