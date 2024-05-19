

const SERVER_NAME = process.env.SERVER_NAME || 'Node HttpServer';
const SERVER_PORT = process.env.SERVER_PORT || 3006;

import http from 'http';
import { Worker, isMainThread, parentPort, workerData }  from 'worker_threads';
import path from 'path';


if (isMainThread) {
  const workerScript = path.resolve(new URL(import.meta.url).pathname); // Получаем путь к скрипту воркера
  const worker = new Worker(workerScript); // Создаем рабочий поток


  worker.on('message', (message) => {
    console.debug(message); // Выводим сообщение из воркера
  });

  // Обработка ошибок, если они возникают в рабочем потоке
  worker.on('error', (err) => {
    console.error('Ошибка в рабочем потоке:', err);
  });

  // Обработка завершения работы рабочего потока
  worker.on('exit', (code) => {
    if (code !== 0)
      console.error(new Error(`Рабочий поток завершился с кодом ошибки ${code}`));
  });



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
      worker.postMessage({ action: 'start' });
      response.writeHead(201);
      response.end();
    }
    else if (request.method === 'GET') {
      worker.postMessage({ action: 'get' });
      worker.once('message', (message) => {
        response.writeHead(200, { 'Content-Type': 'text/plain' });
        response.end(message.toString());
      });
    }
    else  if (request.method === 'DELETE') {
      worker.postMessage({ action: 'stop' });
      response.writeHead(204);
      response.end();
    }


  }).listen(SERVER_PORT, () => {
    console.log(`Server ${SERVER_NAME} running at http://localhost:${SERVER_PORT}/`);
  });
}
else {
  // Этот код выполняется в рабочем потоке

  let taskPtr = 0; // Указатель на задачу
  let cointCounter = 0;

  const doCount = () => {
    cointCounter++;
    console.debug('counter ' + cointCounter)
    taskPtr = setTimeout(doCount, 1000)
  }

  // Получаем сообщения из главного потока
  parentPort.on('message', (message) => {
    if (message.action === 'start' && taskPtr === 0) {
      taskPtr = setTimeout(doCount, 1000); // Запускаем метод doCount
      parentPort.postMessage('Task started');
    }
    if (message.action === 'get' && taskPtr !== 0) {
      parentPort.postMessage(cointCounter);
    }
    if (message.action === 'stop' && taskPtr !== 0) {
      clearTimeout(taskPtr); // Запускаем метод doCount
      parentPort.postMessage('Task stoped');
    }
  });
}
