    // Append functions
    function appendDataLog(dataLog) {

        var info = dataLog.information;
        var data = "";
        for(var i = 0; i <info.length; i++) {
            data += "[name: " + info[i].name + ", data:" + info[i].data+ ", index:" + info[i].index + "]";
        }

        $('#displayDataLogs').prepend(
            '<div class="dataLog">' +
            '[<br/>&nbsp;&nbsp;<strong>id:</strong> ' + dataLog.id + ', <br/>&nbsp;&nbsp;<strong>item_id:</strong> ' + dataLog.item_id + ', <br/>&nbsp;&nbsp;<strong>information:</strong> ' +
            data + ', <br/>&nbsp;&nbsp;<strong>timestamp:</strong>' + dataLog.timestamp + '<br/>]' +
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
        setupDataLogFormData();

        var dataLog = {item_id: $values.item_id, information: JSON.parse($values.information)};

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

    // <<<<< BEGIN: TestScript
    var dataLogScript = false;
    var dataLogScriptLoop;

    $(".create_dataLog_script").on('click', function () {
        console.log("in script");
        dataLogScript = !dataLogScript;
        if (dataLogScript) {
            makeDataLogScript();
            $(".create_dataLog_script").html("Stop");
        }
        else {
            clearInterval(dataLogScriptLoop);
            $(".create_dataLog_script").html("Start");
        }
    });

    function makeDataLogScript() {

        dataLogScriptLoop = setInterval(function () {
            var info = randomDataForTest();
            var dataLog = {item_id: "test_item", information: info};
            myAjaxCalls('/dataLog', 'POST', dataLog);
        }, 1000);
    }

    function randomDataForTest() {
        return [{name: "first_row", data: randomString(), index: 1}, {name: "second_row", data: randomString(), index: 2}, {name: "third_row", data: randomString(), index: 3}];
    }

    function randomString() {
        return Math.random().toString(36).substr(2, 5);
    }

    function setupDataLogFormData() {
        var randomData = JSON.stringify(randomDataForTest());
        $('input[name="information"]').val(randomData);
        $('.info_input_display').html(randomData);
    }
    // >>>>>> END: TestScript

    // Init test page
    getPreviousData();
    connectManagerWebSocket();

    setupDataLogFormData();