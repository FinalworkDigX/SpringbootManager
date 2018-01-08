    // Append functions
    function appendDataLog(dataLog) {
        $('#displayDataLogs').prepend(
            '<div class="dataLog">' +
            '[<strong>id:</strong> ' + dataLog.id + ', <strong>item_id:</strong> ' + dataLog.item_id + ', <strong>information:</strong> ' +
            dataLog.information + ', <strong>timestamp:</strong>' + dataLog.timestamp + ']' +
            '</div>'
        )
    }

    function appendRoom(room) {
        $('#displayRooms').prepend(
            '<form class="delete_room_form">' +
            '[<strong>id:</strong> ' + room.id + ', <strong>name:</strong> ' + room.name + ', <strong>desc:</strong> ' + room.description + ', <strong>loc:</strong>' + room.location + ']' +
            '<input type="hidden" name="id" value="' + room.id + '">' +
            '<button type="submit">Delete</button>' +
            '</form>'
        )
    }

    // Get previous data
    function getPreviousData() {
        $.get('/dataLog').done(
            dataLogs => dataLogs.forEach(appendDataLog)
        );
        $.get('/room').done(
            rooms => rooms.forEach(appendRoom)
        );
    }

    // Submit triggers
    $(document).on('submit', '.create_dataLog_form', function (e) {
        e.preventDefault();
        var $this = $(this);

        var $values = getCleanInputs($this);
        $this.trigger('reset');
        //In the ghetto, in the ghettooooo
        $('input[name="information"]').val(Math.random().toString(36).substr(2, 5));

        var dataLog = {item_id: $values.item_id, information: $values.information};

        myAjaxCalls('/dataLog', 'POST', dataLog);
    });

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

    $(document).on('submit', '.delete_dataLog_form', function (e) {
        e.preventDefault();
        var $this = $(this);
        var $values = getCleanInputs($this);

        myAjaxCalls('/dataLog/' + $values.id, 'DELETE', null);
        $this.remove();
    });

    // New data
    function onNewData(result) {
        var source = result.headers.destination;
        source = source.substring(source.lastIndexOf('/') + 1);
        var dataLog = JSON.parse(result.body);

        switch (source) {
            case 'dataLog':
                appendDataLog(dataLog);
                break;
            case 'room':
                appendRoom(dataLog);
                break;
            default:
                console.log("onNewData: unknown source:" + source);
        }
    }

    function onError(error) {
        console.log(error);
    }

    // Connect to WS
    function connectManagerWebSocket() {
        var socket = new SockJS('/managerWS');
        var stompClient = Stomp.over(socket);
        //stompClient.debug = null;
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/dataLog', onNewData, onError);
            stompClient.subscribe('/topic/room', onNewData, onError)
        });
        // stompClient.heartbeat.incoming = 0
        // stompClient.heartbeat.outgoing = 100
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

    getPreviousData();
    connectManagerWebSocket();