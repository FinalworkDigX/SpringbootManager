
    function appendRoom(room) {
        $('#displayRooms').prepend(
            '<form class="delete_room_form">' +
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

    $(document).on('submit', '.create_room_form', function (e) {
        e.preventDefault();
        var $this = $(this);

        var $values = getCleanInputs($this);
        $this.trigger('reset');

        var room = {name: $values.name, description: $values.description, location: $values.location};

        myAjaxCalls('/room', 'POST', room);
    });

    $(document).on('submit', '.delete_room_form', function (e) {
       e.preventDefault();
       var $this = $(this);
       var $values = getCleanInputs($this);

       myAjaxCalls('/room/' + $values.id, 'DELETE', null);
       $this.remove();
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

    function getCleanInputs(form) {
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