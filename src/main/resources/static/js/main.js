
    function appendRoom(room) {
        $('#messages').append($('<div />').text(room.name + ": " + room.description))
    }

    function getPreviousRooms() {
        $.get('/room').done(
            rooms => rooms.forEach(appendRoom)
        );
    }

    function createRoom() {
        var $nameInput = $('#name');
        var $descriptionInput = $('#description');
        var $locationInput = $('#location');

        var room = {name: $nameInput.val(), description: $descriptionInput.val(), location: $locationInput.val()};

        $nameInput.val('');
        $descriptionInput.val('');
        $locationInput.val('');

        post('/room', room);
        return false;
    }

    function onNewRoom(result) {
        var message = JSON.parse(result.body);
        appendRoom(message);
    }

    function connectWebSocket() {
        var socket = new SockJS('/roomWS');
        var stompClient = Stomp.over(socket);
        //stompClient.debug = null;
        stompClient.connect({}, (frame) => {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/room', onNewRoom);
        });
    }

    function post(url, data) {
        return $.ajax({
            type: 'POST',
            url: url,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            data: JSON.stringify(data)
        })
    }

    getPreviousRooms();
    connectWebSocket();