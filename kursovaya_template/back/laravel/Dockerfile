FROM php:8.3-cli
# Открываем порт для доступа к приложению
EXPOSE 8000

RUN apt update
RUN apt install mc unzip -y

# Внутри контейнера выберем рабочую папку - где будет распологаться мой код
WORKDIR /app

# Установка Composer
RUN curl -sS https://getcomposer.org/installer | php -- --install-dir=/usr/local/bin --filename=composer

# Устанавливаем необходимые расширения PHP
RUN docker-php-ext-install pdo pdo_mysql

# Установка дополнительных расширений PHP для Redis
RUN pecl install redis && docker-php-ext-enable redis
# Установка библиотеки ImageMagick и её заголовочных файлов
RUN apt-get install -y libmagickwand-dev
# Установка расширения Imagick
RUN pecl install imagick && docker-php-ext-enable imagick


# Скопируем код из папки src в контейнер
COPY ./src /app

# Установить зависимости для работы приложения
RUN composer install

# Установить миграции при сборке контейнера
# Механизм плох тем, что к моменту создания контейнера база данных может быть
# еще не запущена
# RUN /app/artisan migrate

# Запустим laravel в режиме разработчика
# Данный подход хорош, когда мне нужно запустить только 1 приложение
# Но если в контейнре нужно выполять их набор - мне нужен другой механизм
# CMD ["/app/artisan", "serve", "--host=0.0.0.0" ,"--port=8000"]

# Установка supervisord
RUN apt-get install -y supervisor
# Копирование конфигурационного файла supervisord для воркеров очереди
COPY ./supervisor.conf /etc/supervisor/conf.d/supervisord.conf
# Запуск сервисов контейнера
CMD ["supervisord", "-c", "/etc/supervisor/conf.d/supervisord.conf"]
