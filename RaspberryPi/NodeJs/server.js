var http = require('http'),
    url = require('url'),
    path = require('path'),
    fs = require('fs');
	io = require("socket.io");
///////////////////////////////////////////////////////////
//create server	
var mimeTypes = {
    "html": "text/html",
    "jpeg": "image/jpeg",
    "jpg": "image/jpeg",
    "png": "image/png",
    "js": "text/javascript",
    "css": "text/css"};
	

var server = http.createServer(function(req, res) {
    var uri = url.parse(req.url).pathname;
	var filePath = "/home/pi/WebServer/htdocs" + uri;
    var filename = path.join(process.cwd(), uri);
    fs.exists(filePath, function(exists) {
        if(!exists) {
            console.log("not exists: " + uri);
            res.writeHead(200, {'Content-Type': 'text/plain'});
            res.write('404 Not Found\n');
            res.end();
			return;
        }
        var mimeType = mimeTypes[path.extname(filePath).split(".")[1]];
        res.writeHead(200, {'Content-Type':mimeType});

        var fileStream = fs.createReadStream(filePath);
        fileStream.pipe(res);

    }); //end path.exists
});
/////////////////////////////////////////////////////////////
//configure socket.io
io.listen(server);

function sendTime() {
    io.emit('time', { time: new Date().toJSON() });
}

// Send current time every 10 secs
setInterval(sendTime, 10000);

io.on('connection', function(socket) {
    // Use socket to communicate with this particular client only, sending it it's own id
    socket.emit('welcome', { message: 'Welcome!', id: socket.id });

    socket.on('i am client', console.log);
});







server.listen(8080);