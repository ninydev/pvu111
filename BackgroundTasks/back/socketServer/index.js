const SERVER_NAME = process.env.SERVER_NAME || 'Node Socket';
const SERVER_PORT = process.env.SERVER_PORT || 3003;

import { Server } from 'socket.io';
const io = new Server({
  cors: {
    origin: "*", // Разрешить все источники
    methods: ["GET", "POST"],
    credentials: true
  }
});

let taskPtr = 0;
let cointCounter = 0;

io.on('connection', (socket) => {
  socket.emit('socket.myNameIs', SERVER_NAME);
  console.debug('connection: ' + socket.handshake.address);


  const doCount = () => {
    cointCounter++;
    console.debug('counter ' + cointCounter)
    socket.emit('updateCount', cointCounter)
    taskPtr = setTimeout(doCount, 1000)
  }

  socket.on('disconnect', data => {
    console.debug('disconnect: ' + socket.handshake.address);
  })

  socket.on('doStart', () => {
    console.debug('doStart');
    if(taskPtr != 0) {
      console.debug('task Running')
    } else {
      console.debug('task run')
      taskPtr = setTimeout(doCount, 1000)
    }

  })

  socket.on('doFinish', () =>{
    console.debug('doFinish');
    if(taskPtr != 0) {
      clearTimeout(taskPtr)
      taskPtr = 0
    }
  })
})

io.listen(SERVER_PORT);