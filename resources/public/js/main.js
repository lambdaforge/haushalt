var socket = new WebSocket("ws://localhost:8088");

socket.onopen = function (event) {
  socket.send("bamboozle?!?");
};

socket.onmessage = function (event) {
  console.log(event.data);
};

