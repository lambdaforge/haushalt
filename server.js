var WebSocketServer = require('ws').Server,
    wss = new WebSocketServer({port: 8088});
var express = require('express');
var app = express();

app.set('port', (process.env.PORT || 8080));
app.use('/', express.static('resources/public'));
app.listen(app.get('port'), function() {
  console.log('Server started: http://localhost:' + app.get('port') + '/');
});

wss.on('connection', function connection(ws) {
  ws.on('message', function incoming(message) {
    console.log('received: %s', message);
  });
  ws.send('bamboozle');
});
