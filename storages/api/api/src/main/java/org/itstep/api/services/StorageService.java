package org.itstep.api.services;

import org.itstep.api.drivers.storages.DriverEnum;
import org.itstep.api.drivers.storages.StorageDriverInterface;
import org.itstep.api.drivers.storages.StorageDriverLocal;
import org.itstep.api.drivers.storages.StorageDriverMinIo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class StorageService {


    private String minioUrl = "http://localhost:9000";


    private String minioUsername = "user";


    private String minioPassword = "password";

    StorageDriverInterface driver;

    private Map<DriverEnum, StorageDriverInterface> drivers;


    public StorageService() {
        System.out.println(" Storage Service Created");
        drivers = new HashMap<>();
        drivers.put(DriverEnum.Local, new StorageDriverLocal());
        drivers.put(DriverEnum.MinIo, new StorageDriverMinIo(minioUrl, minioUsername, minioPassword));

        driver = drivers.get(DriverEnum.MinIo);
    }

    /**
     * Получить экземпляр драйвера для работы с ним напрямую
     * @param disk
     * @return
     */
    public StorageDriverInterface disk (DriverEnum disk) {
        return drivers.get(disk);
    }


    /**
     * Отдавать экземпляр драйвера по умочанию
     * тогда не понадобиться описывать фасад для всего драйвера
     * @return
     */
    public StorageDriverInterface disk () {
        return driver;
    }

    /**
     * Оболочка под драйвер - выбранный по умолчанию
     * @param bucketName
     * @param fileName
     * @param file
     * @return
     */
    public String put (String bucketName, String fileName, MultipartFile file) {
        return driver.put(bucketName,fileName,file);
    }
}
