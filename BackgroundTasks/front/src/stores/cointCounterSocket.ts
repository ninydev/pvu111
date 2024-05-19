import { defineStore } from 'pinia';

import {useToast} from "vue-toast-notification";
const $toast = useToast();


import { io, Socket } from 'socket.io-client';

let socket;

export const useCointCounterWebsocket = defineStore('counterWebSocket', {

  state: () => ({
  }),
  actions: {
    doConnect(){
      const s = io("http://localhost:3003");
      // Реакция на сообщение с сервера
      s.on('socket.myNameIs', (data) => {
        $toast.success('Connect to: ' + data)
      })
      s.on('updateCount', (data) => {
        $toast.success('Count Back Update: ' + data);
      })
      socket = s;
    },
    // since we rely on `this`, we cannot use an arrow function
    doStart() {
      socket.emit('doStart')
    },
    doFinish() {
      socket.emit('doFinish')
    },
  },
})
