
/**
 * Configure microservice
 */
const SERVER_NAME = process.env.SERVER_NAME || 'api.node';
const SERVER_PORT = process.env.SERVER_PORT || 3003;

/**
 * Configure microservice
 */
const MINIO_SERVER = process.env.MINIO_SERVER || 'vpu111.storage';
const MINIO_PORT = process.env.MINIO_PORT || 9000;
const MINIO_ACCESS_KEY = process.env.MINIO_ACCESS_KEY || 'vhB3mZnWXuXjvdZaoWeu';
const MINIO_SECRET_KEY = process.env.MINIO_SECRET_KEY || 'HW1LYFOzeNE4ce6OjK52rUs4hA4fTTRQEsyytwWY';



/**
 * Modules
 */
import http from 'http';

// let Minio = require('minio')
import * as Minio from 'minio';

let minioClient = new Minio.Client({
    endPoint: MINIO_SERVER,
    port: MINIO_PORT,
    useSSL: false,
    accessKey: MINIO_ACCESS_KEY,
    secretKey: MINIO_SECRET_KEY,
});

http.createServer(
    (request, response) => {

        console.log("Request");

        minioClient.bucketExists('files', function (err, exists) {
            if (err) {
                return console.log(err);
            }
            if (exists) {
                return console.log('Bucket exists.');
            }
            minioClient.makeBucket('files', 'us-east-1', function (err) {
                if (err) {
                    return console.log('Error creating bucket.', err);
                }
                console.log('Bucket created successfully in "us-east-1".');
            });
        });

        try {
            let content = "Hello World";
            // Записываем содержимое в файл
            minioClient.putObject('files', 'helloWorld.txt', content, content.length, 'text/plain')
                .then(res => {
                    console.log(res);
                    console.log(`Файл  успешно создан и записан в бакет `);

                    response.end("Hello NodeJS: " + SERVER_NAME);

                });


        } catch (err) {
            console.error('Произошла ошибка:', err);
        }
        // response.end("Hello NodeJS: " + SERVER_NAME)
}).listen(SERVER_PORT)
