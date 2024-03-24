package org.itstep.api.drivers.storages;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public class StorageDriverMinIo implements StorageDriverInterface
{

    /**
     * Выполняет подключение к хранилищу
     * @param minioUrl
     * @param minioUsername
     * @param minioPassword
     */

    public StorageDriverMinIo(String minioUrl,String minioUsername, String minioPassword ) {
        MinioClient client = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioUsername, minioPassword)
                .build();

        System.out.println(" Storage MinioClient Created");
    }


    /**
     * Выгружает файл в хранилище
     * @param bucketName
     * @param fileName
     * @param file
     * @return
     */
    @Override
    public String put(String bucketName, String fileName, MultipartFile file) {
        return null;
    }
}
