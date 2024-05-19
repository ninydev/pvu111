import { defineStore } from 'pinia';

import {useToast} from "vue-toast-notification";
const $toast = useToast();


export const useCointCounter = defineStore('counterLocal', {
  state: () => ({
    taskPtr: 0,
    cointCounter: 0,
  }),
  actions: {
    doCoint ()  {
      this.cointCounter++;
      console.log(this.cointCounter)
      $toast.success("Count: " + this.cointCounter);
      this.taskPtr = setTimeout(this.doCoint, 1000);
    },
    // since we rely on `this`, we cannot use an arrow function
    doStart() {
      if (this.taskPtr != 0) {
        $toast.error("Task is started");
        return;
      }
      this.taskPtr = setTimeout(this.doCoint, 1000);
    },
    doFinish() {
      if (this.taskPtr == 0) {
        $toast.error("Task is not started");
        return;
      }

      clearInterval(this.taskPtr);
      this.taskPtr = 0;
    },
  },
})
