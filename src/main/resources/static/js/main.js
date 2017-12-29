
    function appendRoom(room) {
    console.log(room);
        $('#displayRooms').append(
            '<form class="delete_form">' +
            '[<strong>id:</strong> ' + room.id + ', <strong>name:</strong> ' + room.name + ', <strong>desc:</strong> ' + room.description + ', <strong>loc:</strong>' + room.location + ']' +
            '<input type="hidden" name="id" value="' + room.id + '">' +
            '<button type="submit">Delete</button>' +
            '</form>'
        )
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

        myAjaxCalls('/room', 'POST', room);
        return false;
    }

    $(document).on('submit', '.delete_form', function (e) {
       //e.preventDefault();
       var $values = cleanInputs($(this));
       var room = {"id": $values.id};

       myAjaxCalls('/room', 'DELETE', room);
    });

    function onNewRoom(result) {
        var message = JSON.parse(result.body);
        appendRoom(message);
    }

    function connectWebSocket() {
        var socket = new SockJS('/roomWS');
        var stompClient = Stomp.over(socket);
        //stompClient.debug = null;
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/room', onNewRoom);
        });
    }

    function cleanInputs(form) {
        var values = {};

        $.each(form.serializeArray(), function (i, field) {
            values[field.name] = field.value;
        });

        return values
    }

    function myAjaxCalls(url, method, data) {
        return $.ajax({
            type: method,
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