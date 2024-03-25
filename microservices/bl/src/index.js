const httpSrv = require("http")

const SERVER_NAME= process.env.SERVER_NAME

const serverInstance = httpSrv.createServer(
    function (request, response) {
        console.log("Incoming")
        response.end("Task completed by: " + SERVER_NAME)
    }
)

serverInstance.listen(3000, function (err)  {
    console.log("Server " + SERVER_NAME + " Start " + "http://localhost:3000")
})
