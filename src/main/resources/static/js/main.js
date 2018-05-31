
    const remote = "/api";
    console.log('url prefix ="' + remote + '"');

    const DATALOG_URL = remote + "/v1/dataLog";
    const WS_URL = remote + "/managerWS";
    // WS does NOT require prefix.
    const WS_ECHO = "/app/echo";
    const WS_BEACON = '/app/beacon';
    const WS_ROOM = '/app/room';

    // Append functions
    function appendDataLog(dataLog) {

        var info = dataLog.information;
        var data = "";
        for(var i = 0; i <info.length; i++) {
            data += "[name: " + info[i].name + ", data:" + info[i].data+ ", index:" + info[i].index + "]";
        }

        $('#displayDataLogs').prepend(
            '<div class="dataLog">' +
            '[<br/>&nbsp;&nbsp;<strong>id:</strong> ' + dataLog.id + ', <br/>&nbsp;&nbsp;<strong>itemId:</strong> ' + dataLog.itemId + ', <br/>&nbsp;&nbsp;<strong>information:</strong> ' +
            data + ', <br/>&nbsp;&nbsp;<strong>timestamp:</strong>' + dataLog.timestamp + '<br/>]' +
            '</div>'
        )
    }

    // Get previous data
    function getPreviousData() {
        $.get(DATALOG_URL).done(
            dataLogs => dataLogs.forEach(appendDataLog)
        );
    }

    // Submit triggers
    $(document).on('submit', '#create_dataLog_form', function (e) {
        e.preventDefault();
        var $this = $(this);

        var $values = getCleanInputs($this);
        //$this.trigger('reset');
        //In the ghetto, in the ghettooooo
        setupDataLogFormData();

        var dataLog = {itemId: $values.item_id, information: JSON.parse($values.information)};


        console.log('datalog : ' + JSON.stringify(dataLog));

        myAjaxCalls(DATALOG_URL, 'POST', dataLog);
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
            default:
                console.log("onNewData: unknown source:" + source);
        }
    }

    function onError(error) {
        console.log(error);
    }
    var stompClient;
    // Connect to WS
    function connectManagerWebSocket() {
        var socket = new SockJS(WS_URL);
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/dataLog', onNewData, onError);
            stompClient.subscribe('/topic/room', onNewData, onError);
            stompClient.subscribe('/topic/beacon/test-id/calibrate', onNewData, onError);
            stompClient.subscribe('/topic/beacon/test-id/getByMajorMinor', onNewData, onError);
            stompClient.subscribe('/topic/beacon', onNewData, onError);
            stompClient.subscribe('/topic/room/test-id', onNewData, onError);
            //
            stompClient.subscribe('/topic/echo/ws', onNewData, onError);
            stompClient.subscribe('/topic/echo/sport', onNewData, onError);
            stompClient.subscribe('/topic/echo/cafe', onNewData, onError);
            stompClient.subscribe('/topic/echo/hospital', onNewData, onError);
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


    // ==================================== //
    //                                      //
    //       TEST / DEBUG - FUNCTIONS       //
    //                                      //
    // ==================================== //

    // Default values
    function randomInt(min = 0, max = 100) {
        return Math.random() * (max - min) + min;
    }

    function randomString() {
        return Math.random().toString(36).substr(2, 5);
    }

    function setupDataLogFormData() {
        var randomData = JSON.stringify(randomDataForTest());
        $('input[name="information"]').val(randomData);
        $('.info_input_display').html(randomData);
    }

    // ------------------------------------ //
    //        DataLog test functions        //
    // ------------------------------------ //
    var dataLogScript = false;
    var dataLogScriptLoop;

    $("#create_dataLog_script").on('click', function () {
        console.log("in DataLog script");
        dataLogScript = !dataLogScript;
        if (dataLogScript) {
            var dlid = $('#item_id').val();
            makeDataLogScript(dlid);
            $(".create_dataLog_script").html("Stop");
        }
        else {
            clearInterval(dataLogScriptLoop);
            $(".create_dataLog_script").html("Start");
        }
    });

    function makeDataLogScript(itemId) {

        dataLogScriptLoop = setInterval(function () {
            var info = randomDataForTest();
            var dataLog = {itemId: itemId, information: info};
            myAjaxCalls(DATALOG_URL, 'POST', dataLog);
        }, 1000);
    }

    function randomDataForTest() {
        return [{name: "first_row", data: randomString(), index: 1}, {name: "second_row", data: randomString(), index: 2}, {name: "third_row", data: randomString(), index: 3}];
    }

    // ------------------------------------ //
    //       WebSocket test functions       //
    // ------------------------------------ //


    // =======  External WS Script  ======= //
    var externalWSScript = false;
    var externalWSScriptLoop;

    $("#ws_simulator_button").on('click', function () {
        console.log("in Simulate External WS script");
        externalWSScript = !externalWSScript;
        if (externalWSScript) {
            simulateExternalWSScript();
            $("#ws_simulator_button").html("Stop");
        }
        else {
            clearInterval(externalWSScriptLoop);
            $("#ws_simulator_button").html("Start");
        }
    });

    function simulateExternalWSScript() {

        externalWSScriptLoop = setInterval(function () {
            testExternalWSSimulation();
        }, 1000);
    }

    function testExternalWSSimulation() {
        var test = {
            id: "qnix-qx2710led-1",
            type: "screen",
            use_info: {
                on_time: new Date().getSeconds(),
                temp: randomInt(25, 27).toFixed(2)
            },
            item_info: {
                purchased: 1288323623006,
                warranty: 5
            }
        };

        stompClient.send(WS_ECHO + '/ws', {priority: 9}, JSON.stringify(test))
    }


    // ======  Sport scenario Script  ===== //
    var sportScenarioScript = false;
    var sportScenarioScriptLoop;

    $("#ws_sport_scenario_button").on('click', function () {
        console.log("in Sport Scenario Script");
        sportScenarioScript = !sportScenarioScript;
        if (sportScenarioScript) {
            simSportScenarioScript();
            $("#ws_sport_scenario_button").html("Stop");
        }
        else {
            clearInterval(sportScenarioScriptLoop);
            $("#ws_sport_scenario_button").html("Start");
        }
    });

    function simSportScenarioScript() {
        sportScenarioScriptLoop = setInterval(function () {
            testSportSim();
        }, 1000);
    }

    function testSportSim() {
        var test = {
            equipment_id: "treadmill_21",
            user: {
                info: {
                    name: "Jeff",
                    age: 34,
                    membership: {
                        from: new Date().setFullYear(2016, 4, 12),
                        to: new Date().setFullYear(2019, 1, 1)
                    }
                },
                stats: {
                    time: new Date().getSeconds(),
                    kcal_burned: new Date().getSeconds() * 2.5,
                    heart_rate: Math.round(randomInt(110, 115))
                }
            }
        };

        stompClient.send(WS_ECHO + '/sport', {priority: 9}, JSON.stringify(test))
    }


    // ======  Cafe scenario Script  ===== //
    var cafeScenarioScript = false;
    var cafeScenarioScriptLoop;

    $("#ws_cafe_scenario_button").on('click', function () {
        console.log("in Sport Scenario Script");
        cafeScenarioScript = !cafeScenarioScript;
        if (cafeScenarioScript) {
            simCafeScenarioScript();
            $("#ws_cafe_scenario_button").html("Stop");
        }
        else {
            clearInterval(cafeScenarioScriptLoop);
            $("#ws_cafe_scenario_button").html("Start");
        }
    });

    function simCafeScenarioScript() {
        cafeScenarioScriptLoop = setInterval(function () {
            testCafeSim();
        }, 1000);
    }

    function testCafeSim() {
        var test = {
            tap_id: "tap_3",
            draught: {
                brand: "Jupiler"
            },
            stats: {
                temp: randomInt(2, 4).toFixed(2),
                pressure: randomInt(10, 12).toFixed(3),
                liter: new Date().getSeconds(),
                reserve: 4
            }
        };

        stompClient.send(WS_ECHO + '/cafe', {priority: 9}, JSON.stringify(test))
    }


    // ======  Hospital scenario Script  ===== //
    var hospitalScenarioScript = false;
    var hospitalScenarioScriptLoop;

    $("#ws_hospital_scenario_button").on('click', function () {
        console.log("in Hospital Scenario Script");
        hospitalScenarioScript = !hospitalScenarioScript;
        if (hospitalScenarioScript) {
            simHospitalScenarioScript();
            $("#ws_hospital_scenario_button").html("Stop");
        }
        else {
            clearInterval(hospitalScenarioScriptLoop);
            $("#ws_hospital_scenario_button").html("Start");
        }
    });

    function simHospitalScenarioScript() {
        hospitalScenarioScriptLoop = setInterval(function () {
            testHospitalSim();
        }, 1000);
    }

    function testHospitalSim() {
        var test1 = {
            room_id: "room_246",
            patient: {
                name: "Diane",
                age: 27,
                period: {
                    from: new Date().setFullYear(2018, 4, 12),
                    to: new Date().setFullYear(2018, 4, 16)
                },
                reason: "R - Ulna"
            },
            stats: {
                heart_rate: Math.round(randomInt(61, 65)),
                blood_pressure: Math.round(randomInt(117, 120)) + '-' + Math.round(randomInt(77, 80))
            }
        };

        var test2 = {
            room_id: "room_247",
            patient: {
                name: "Louis",
                age: 32,
                period: {
                    from: new Date().setFullYear(2018, 4, 10),
                    to: new Date().setFullYear(2018, 4, 15)
                },
                reason: "L - rib 7"
            },
            stats: {
                heart_rate: Math.round(randomInt(71, 73)),
                blood_pressure: Math.round(randomInt(135, 137)) + '-' + Math.round(randomInt(88, 91))
            }
        };

        var test3 = {
            room_id: "room_248",
            patient: {
                name: "Chloe",
                age: 64,
                period: {
                    from: new Date().setFullYear(2018, 4, 13),
                    to: new Date().setFullYear(2018, 4, 14)
                },
                reason: "L - tibia"
            },
            stats: {
                heart_rate: Math.round(randomInt(84, 86)),
                blood_pressure: Math.round(randomInt(107, 110)) + '-' + Math.round(randomInt(75, 78))
            }
        };

        stompClient.send(WS_ECHO + '/hospital', {priority: 9}, JSON.stringify(test1));
        stompClient.send(WS_ECHO + '/hospital', {priority: 9}, JSON.stringify(test2));
        stompClient.send(WS_ECHO + '/hospital', {priority: 9}, JSON.stringify(test3));
    }



    // ------------------------------------ //
    //        Beacon test functions         //
    // ------------------------------------ //
    function testCalibrate(message, float) {

        var test = {
            id: message,
            name: "test_beacon - " + message,
            calibrationFactor: float
        };

        stompClient.send(WS_BEACON + "/test-id/calibrate", {priority: 9}, JSON.stringify(test));
    }

    // ------------------------------------ //
    //         Room test functions          //
    // ------------------------------------ //
    function testRoomForRa(room_id) {
        //8f85bc69-fdcc-43fc-aaa9-c9563d42ae6e
        var test = {
            roomLocation: {
                x: 1.3,
                y: 1.4,
                z: 1.5
            }
        };

        stompClient.send(WS_ROOM + "/test-id/" + room_id, {priority: 9}, JSON.stringify(test));
    }

    // Init test page
    getPreviousData();
    connectManagerWebSocket();

    setupDataLogFormData();