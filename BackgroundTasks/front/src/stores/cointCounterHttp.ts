import { defineStore } from 'pinia';

import {useToast} from "vue-toast-notification";
const $toast = useToast();


export const useCointCounterHttp = defineStore('counterHttp', {

  state: () => ({
    taskPtr: 0,
    cointCounter: 0,
  }),
  actions: {
    onUpdate() {
      fetch('http://localhost:3005', {
        method: 'GET'
      }).then(res => {
        if (res.status !== 200 ){
          $toast.error(res.statusText)
          return '-1';
        }
        return res.text();
      })
        .then( c => {
          if (c != '-1') {
            $toast.success('Update ' + c);
          }
        })
    },
    // since we rely on `this`, we cannot use an arrow function
    doStart() {
      fetch('http://localhost:3005', {
        method: 'POST'
      }).then(res => {
        if (res.status !== 201 ){
          $toast.error(res.statusText)
          return;
        }
        this.taskPtr = setInterval(this.onUpdate, 1000);
      })
    },
    doFinish() {
      fetch('http://localhost:3005', {
        method: 'DELETE'
      }).then(res => {
        if (res.status !== 204 ){
          $toast.error(res.statusText)
          return;
        }
        clearTimeout(this.taskPtr);
      })
    },
  },
})
