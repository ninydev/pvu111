let counter = 0;

export async function workerFun() {
  counter++;
  console.log('worker: ' + counter);
}

// const intervalId = setInterval(doUpdate, 1000);
//
// onmessage = function(event) {
//   if (event.data === 'stop') {
//     clearInterval(intervalId);
//     close();
//   }
// };
