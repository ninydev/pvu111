package org.itstep.api.drivers.storages;

import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;



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


    public String url(String bucketName, String fileName) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException, BucketPolicyTooLargeException {

        // Получаем информацию о ведре (bucket)
        String config =
                minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(bucketName).build());
        System.out.println("BucketPolicy: " + config);


        String url =
                minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucketName)
                                .object(fileName)
                                .expiry(2, TimeUnit.HOURS)
                                .build());
        return url;
    }

    public String url(String bucketName, String fileName, int duration, TimeUnit unit) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String url =
                minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(bucketName)
                                .object(fileName)
                                .expiry(duration, unit)
                                .build());
        return url;
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

            // Тип контента
            String contentType = file.getContentType();

            // Отправляем байты файла в хранилище
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName).stream(bais, bais.available(), -1)
                            .contentType(contentType) // Устанавливаем content-type
                            .build());
            bais.close();

            return url(bucketName, fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null; // Ошибка загрузки файла
        }

    }
}
