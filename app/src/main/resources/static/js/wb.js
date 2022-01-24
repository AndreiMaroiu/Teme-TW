var stompClient = null;

$(document).ready(function() {
    connect();
});

function connect() {
    var socket = new SockJS('/our-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);

        stompClient.subscribe('/topic/messages', function (message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/user/topic/private-messages', function (message) {
            showPrivateMessage(JSON.parse(message.body));
        });
    },  function (message){
       //alert("disconnected!");
    });

    // socket.onclose = function (){
    //   alert("socket closed!");
    // };
}

function showMessage(message) {
    alert(message);
}

function showPrivateMessage(message)
{
    if (message.content.startsWith("Stock empty"))
    {
        let not = document.getElementById("notification");
        not.style.display = 'block';

        let text = document.getElementById("notification-text");
        text.innerText = message.content;

        let refill = document.getElementById("notification-refill");
        refill.href = "/trader/refill?id=" + message.id;
    }
    else
    {
        alert(message.content);
    }
}

function sendMessage() {
    console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({'content': 'hello!'}));
}

function sendPrivateMessage() {
    console.log("sending private message");
    stompClient.send("/ws/private-message", {}, JSON.stringify({'content': 'Stock empty for tea', 'id': 5}));
}