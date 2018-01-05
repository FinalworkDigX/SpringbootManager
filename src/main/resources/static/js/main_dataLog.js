
    function appendDataLog(dataLog) {
        $('#displayDataLogs').prepend(
            '<div class="dataLog">' +
            '[<strong>id:</strong> ' + dataLog.id + ', <strong>item_id:</strong> ' + dataLog.item_id + ', <strong>information:</strong> ' +
            dataLog.information + ', <strong>timestamp:</strong>' + dataLog.timestamp + ']' +
            '</div>'
        )
    }

    function getPreviousDataLogs() {
        $.get('/dataLog').done(
            rooms => rooms.forEach(appendDataLog)
        );
    }

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

    $(document).on('submit', '.delete_dataLog_form', function (e) {
       e.preventDefault();
       var $this = $(this);
       var $values = getCleanInputs($this);

       myAjaxCalls('/dataLog/' + $values.id, 'DELETE', null);
       $this.remove();
    });

    function onNewDataLog(result) {
        var dataLog = JSON.parse(result.body);
        appendDataLog(dataLog);
    }

    function connectDataLogWebSocket() {
        var socket = new SockJS('/dataLogWS');
        var stompClient = Stomp.over(socket);
        //stompClient.debug = null;
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/dataLog', onNewDataLog);
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

    getPreviousDataLogs();
    connectDataLogWebSocket();