const SERVER_NAME = process.env.SERVER_NAME || 'Node HttpServer';
const SERVER_PORT = process.env.SERVER_PORT || 3005;

import http from 'http';

let taskPtr = 0;
let cointCounter = 0;

const doCount = () => {
  cointCounter++;
  console.debug('counter ' + cointCounter)
  taskPtr = setTimeout(doCount, 1000)
}


http.createServer((request, response) => {

  // Установка заголовков CORS
  response.setHeader('Access-Control-Allow-Origin', '*'); // Разрешить все источники
  response.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, OPTIONS'); // Разрешить методы
  response.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization'); // Разрешить заголовки

  // Обработка предзапросов CORS (preflight requests)
  if (request.method === 'OPTIONS') {
    response.writeHead(204);
    response.end();
    return;
  }

  if(request.method === 'POST') {
    console.debug('doStart');
    if(taskPtr != 0) {
      console.debug('task Running')
      response.writeHead(418);
      response.end('Task Running');
    } else {
      console.debug('task run')
      taskPtr = setTimeout(doCount, 1000)
      response.writeHead(201);
      response.end('Task Running');
    }
  }
  else if (request.method === 'GET') {
    console.debug('get counter ' + cointCounter)
    response.writeHead(200, { 'Content-Type': 'text/plain' });
    response.end('' + cointCounter);
  }
  else  if (request.method === 'DELETE') {
    console.debug('doFinish');
    if(taskPtr != 0) {
      clearTimeout(taskPtr);
      taskPtr = 0;
    }
    response.writeHead(204);
    response.end();
  }
  else {
    response.writeHead(418);
    response.end();
  }


}).listen(SERVER_PORT, () => {
  console.log(`Server ${SERVER_NAME} running at http://localhost:${SERVER_PORT}/`);
});