
/**
 * Configure microservice
 */
const SERVER_NAME = process.env.SERVER_NAME || 'api.node';
const SERVER_PORT = process.env.SERVER_PORT || 3003;

/**
 * Modules
 */
import http from 'http';


http.createServer(
    (request, response) => {
    response.end("Hello NodeJS: " + SERVER_NAME)
}).listen(SERVER_PORT)
