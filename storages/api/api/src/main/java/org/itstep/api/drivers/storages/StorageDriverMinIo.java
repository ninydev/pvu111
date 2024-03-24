package org.itstep.api.drivers.storages;

import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class StorageDriverMinIo implements StorageDriverInterface
{

    private  MinioClient minioClient;

    /**
     * Выполняет подключение к хранилищу
     * @param minioUrl
     * @param minioUsername
     * @param minioPassword
     */

    public StorageDriverMinIo(String minioUrl,String minioUsername, String minioPassword ) {
        minioClient = MinioClient.builder()
                .endpoint(minioUrl)
                .credentials(minioUsername, minioPassword)
                .build();

        System.out.println(" Storage MinioClient Created");
    }

    /**
     * Проверяем - есть ли нужное нам хранилище (контейнер, ведро) на сервере
     * Если его нет - создаем
     * Это аналог directory.mkdirs - при локальном хранилище
     * @param bucketName
     */
    private void bucketExists(String bucketName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found =
                minioClient.bucketExists(
                        BucketExistsArgs.builder().bucket(bucketName).build());
        if (!found) {
            // Make a new bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
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
        try {
            // Создаем контейнер (ведро), если он не существует
            bucketExists(bucketName);

            // Получаем байты файла
            ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());

            // Отправляем байты файла в хранилище
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName).stream(bais, bais.available(), -1)
                            .build());
            bais.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null; // Ошибка загрузки файла
        }
        return null;
    }
}
