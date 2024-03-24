package org.itstep.api.drivers.storages;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * Код работы системы с локальной папкой
 */
public class StorageDriverLocal implements StorageDriverInterface
{
    // Путь к локальной папке на сервере, где будут храниться файлы
    private static final String localStorageDir = "/home/keeper/temp/spring";

    @Override
    public boolean put(String bucketName, String fileName, MultipartFile file) {
        try {
            // Определяем путь к папке, куда будем сохранять файл
            String uploadDir = localStorageDir + File.separator + bucketName;

            // Создаем директорию, если она не существует
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Получаем байты файла
            byte[] bytes = file.getBytes();

            // Создаем путь к файлу
            Path filePath = Paths.get(uploadDir + File.separator + fileName);

            // Записываем байты файла в созданный путь
            Files.write(filePath, bytes);

            return true; // Файл успешно загружен
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Ошибка загрузки файла
        }
    }
}
