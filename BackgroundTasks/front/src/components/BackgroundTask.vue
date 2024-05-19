<script setup lang="ts">

import { useCointCounter } from '@/stores/cointCounter'
import { useCointCounterWebsocket } from '@/stores/cointCounterSocket'
import { onMounted } from 'vue'
import { useCointCounterHttp } from '@/stores/cointCounterHttp'

const cointCounter = useCointCounter();
const  cointCounterSocket = useCointCounterWebsocket();
const  cointCounterHttp = useCointCounterHttp();

onMounted(() => {
  cointCounterSocket.doConnect();
  if(window.Worker) {
    console.log('worker start')
  }
  else {
    console.error('Web Workers are not supported in this browser.');
}
})


const doStartHttp = () => {
  cointCounterHttp.doStart();
}

const doFinishHttp = () => {
  cointCounterHttp.doFinish()
}

const doStart = () => {
  cointCounter.doStart();
}

const doFinish = () => {
cointCounter.doFinish()
}
const doStartSocket = () => {
  cointCounterSocket.doStart();
}

const doFinishSocket = () => {
  cointCounterSocket.doFinish()
}
</script>

<template>
  <div>
    <button @click="doStart">Start</button>
    <button @click="doFinish">Finish</button>
  </div>
  <div>
    <button @click="doStartSocket">Start Socket</button>
    <button @click="doFinishSocket">Finish Socket</button>
  </div>
  <div>
    <button @click="doStartHttp">Start Http</button>
    <button @click="doFinishHttp">Finish Http</button>
  </div>
</template>

<style scoped>

</style>