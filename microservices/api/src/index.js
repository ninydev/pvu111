const httpSrv = require("http")
const axios = require("axios")

const SERVER_NAME= process.env.SERVER_NAME

const serverInstance = httpSrv.createServer(
    function (request, response) {
        // Было так - все запросы обрабатывает сервер с апи
    //     response.end("Task completed by: " + SERVER_NAME)

        // Я передаю задачу на прямую (по RESTful api) другой машине - пусть она считает
        axios.get("http://router.bl.pv111")
            .then(res=> {
                console.log (res)
                response.end(res.data);
            })
            .catch(err=> {
                console.error(err.toString())
                response.end(err.toString())
            })
    }
)

serverInstance.listen(3000, function (err)  {
    console.log("Server + bl " + SERVER_NAME + " Start " + "http://localhost:3000")
})
