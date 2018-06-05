# RAM - API &amp; WS documentation
Documentation is split between API and Web Sockets endpoints. Link to each group can be found in the summary.<br/>
For this backend application to fully work, a working copy of _security.properties_ will be needed in the resources.

## Summary
1. [Working with websockets](#web-socket-basics)
1. [Recurring errors](#recurring-errors)
1. [Auth0 management](#auth0-management-to-top-)
1. [Authentication](#authentication-to-top-)
1. [Room](#room-to-top-)
1. [Beacon](#beacon-to-top-)
1. [DataItem](#dataitem-to-top-)
1. [DataSource](#datasource-to-top-)
1. [DataLog](#datalog-to-top-)
1. [DataItemRequest](#dataitemrequest-to-top-)
1. [Debug](#debug-to-top-)
1. [System Monitoring](#system-monitoring-to-top-)
1. [Notifications](#notifications-to-top-)

***********************************************************************************************************************
## Web Socket basics
Web sockets currently need no credentials to be used. This may changed as the backend evolves.<br/>
This Backend application uses [STOMP](https://stomp.github.io/) ease the use of the Web Socket endpoints.

### How to use
#### Javascript Example (Using SockJS)
```javascript
var stompClient;

function connectManagerWebSocket() {
    var socket = new SockJS(WS_URL);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/dataLog', onNewData, onError);
        stompClient.subscribe('/topic/room', onNewData, onError);
    });
    // stompClient.heartbeat.incoming = 0
    // stompClient.heartbeat.outgoing = 100
}

connectManagerWebSocket();
```

### Summary of models using Web Sockets
1. [Room](#room-ws)
1. [Beacon](#beacon-ws)
1. [DataLog](#datalog-ws)
1. [Debug](#debug-ws)
1. [System Monitoring](#sysMon-ws)
1. [Notifications](#notifications-ws)

***********************************************************************************************************************
## Recurring errors
### 400
occurs when there is no existing slug to the asked method

Example:
```json
{
	"timestamp": 1527686103180,
	"status": 400,
	"error": "Bad Request",
	"message": "No message available",
	"path": "/v1/dataLogd"
}
```

### 401
occurs when trying to access a protected slug without a valid jwt token, check:
1. Validity of jwt token
2. Correct token format: "Bearer jwt.token"

Example:
```json
{
	"timestamp": 1527686103180,
	"status": 401,
	"error": "Unauthorized",
	"message": "No message available",
	"path": "/v1/dataLogd"
}
```

### 404
Occurs when a wrong slug is presented to the api, check:
1. If the syntax is correct
2. If the requested slug exists

Example:
```json
{
	"timestamp": 1527686103180,
	"status": 404,
	"error": "Not Found",
	"message": "No message available",
	"path": "/v1/dataLogd"
}
```
<br/>
<br/>

***********************************************************************************************************************
## Auth0 management [To Top ^](#summary)
### Base slug
[_base_url_] /v1/management/user

### Model: User
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

parameter       |Type           |required
----------------|---------------|:--------:
client_id       |String         |Yes
connection      |String         |Yes
password        |String         |Yes
verify_password |Boolean        |Yes
username        |String         |Yes
email           |String         |Yes
Verify_email    |Boolean        |Yes
identities      |List - Identity|Yes
email_verified  |Boolean        |No
phone_number    |String         |No
phone_verified  |Boolean        |No
user_id         |String         |No
picture         |String         |No
name            |String         |No
nickname        |String         |No
given_name      |String         |No
family_name     |String         |No
created_at      |Date - String  |No
updated_at      |Date - String  |No
app_metadata    |JSON - String  |No
user_metadata   |JSON - String  |No
multifactor     |List - String  |No
last_ip         |String         |No
last_login      |Date - String  |No
logins_count    |Integer        |No
blocked         |Boolean        |No

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/management/user
* Method: **GET**
* Body: [User](#model-user)

#### Returns
```json
[
	{
		"email": "pudi1711@hotmail.com",
		"email_verified": false,
		"user_id": "auth0|5aaaad170724cf32a028e7ea",
		"picture": "https://s.gravatar.com/avatar/b79ars%2Fpu.png",
		"name": "pudi17@hotmail.com",
		"nickname": "pudi17",
		"created_at": "2018-03-15T17:27:51.311Z",
		"updated_at": "2018-05-30T12:42:36.808Z",
		"identities": [
			{
				"connection": "Username-Password-Authentication",
				"user_id": "5aaaad170724cf32a028e7ea",
				"provider": "auth0",
				"isSocial": false
			}
		],
		"app_metadata": {},
		"user_metadata": {
			"channel": "1ba02d07-11f8-4325-b3a3-992473a3c0e0",
			"type": "admin"
		},
		"last_ip": "94.224.39.64",
		"last_login": "2018-05-30T12:42:36.808Z",
		"logins_count": 666
	},
	{
	...
	}
]
```
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/management/user
* Method: **POST**
* Body: [User](#model-user)

#### Returns
```json
{
    "email": "pudi1711@hotmail.com",
    "email_verified": false,
    "user_id": "auth0|5aaaad170724cf32akjdi7ea",
    "picture": "https://s.gravatar.com/avatar/b79ars%2Fpu.png",
    "name": "pudi17@hotmail.com",
    "nickname": "pudi17",
    "created_at": "2018-01-15T17:27:51.311Z",
    "updated_at": "2018-09-30T12:42:36.808Z",
    "identities": [
        {
            "connection": "Username-Password-Authentication",
            "user_id": "5aaaad170724cf32akjdi7ea",
            "provider": "auth0",
            "isSocial": false
        }
    ],
    "app_metadata": {},
    "user_metadata": {
        "channel": "1ba02d07-lkjh-4325-b3a3-992473a3c0e0",
        "type": "admin"
    },
    "last_ip": "94.224.01.01",
    "last_login": "2018-05-30T12:42:36.808Z",
    "logins_count": 669
}
```
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

### &gt;_Edit_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/management/user
* Method: **PUT**
* Body: [User](#model-user)

#### Returns
```json
{
    "email": "pudi1711@hotmail.com",
    "email_verified": false,
    "user_id": "auth0|5aaaad170724cf32akjdi7ea",
    "picture": "https://s.gravatar.com/avatar/b79ars%2Fpu.png",
    "name": "pudi17@hotmail.com",
    "nickname": "pudi17",
    "created_at": "2018-01-15T17:27:51.311Z",
    "updated_at": "2018-09-30T12:42:36.808Z",
    "identities": [
        {
            "connection": "Username-Password-Authentication",
            "user_id": "5aaaad170724cf32akjdi7ea",
            "provider": "auth0",
            "isSocial": false
        }
    ],
    "app_metadata": {},
    "user_metadata": {
        "channel": "1ba02d07-lkjh-4325-b3a3-992473a3c0e0",
        "type": "admin"
    },
    "last_ip": "94.224.01.01",
    "last_login": "2018-05-30T12:42:36.808Z",
    "logins_count": 669
}
```
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

### &gt;_Delete_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/management/user
* Method: **DELETE**
* Body: [User](#model-user)

#### Returns
```json
{
    "email": "pudi1711@hotmail.com",
    "email_verified": false,
    "user_id": "auth0|5aaaad170724cf32akjdi7ea",
    "picture": "https://s.gravatar.com/avatar/b79ars%2Fpu.png",
    "name": "pudi17@hotmail.com",
    "nickname": "pudi17",
    "created_at": "2018-01-15T17:27:51.311Z",
    "updated_at": "2018-09-30T12:42:36.808Z",
    "identities": [
        {
            "connection": "Username-Password-Authentication",
            "user_id": "5aaaad170724cf32akjdi7ea",
            "provider": "auth0",
            "isSocial": false
        }
    ],
    "app_metadata": {},
    "user_metadata": {
        "channel": "1ba02d07-lkjh-4325-b3a3-992473a3c0e0",
        "type": "admin"
    },
    "last_ip": "94.224.01.01",
    "last_login": "2018-05-30T12:42:36.808Z",
    "logins_count": 669
}
```
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>
<br/>
<br/>

## Authentication [To Top ^](#summary)
### Base slug
[_base_url_] /v1/auth
### Model: Create
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

parameter       |Type           |required
----------------|---------------|:--------:
_id             |String         |Yes
email           |String         |Yes
email_verified  |Boolean        |Yes

### Model Token holder
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

parameter       |Type           |required
----------------|---------------|:--------:
access_token    |String         |Yes
id_token        |String         |Yes
refresh_token   |String         |Yes
token_type      |String         |Yes
expires_in      |Double         |Yes

### DTO: Login

parameter       |Type           |required
----------------|---------------|:--------:
email           |String         |Yes
password        |String         |Yes

### API
### &gt;_Sign up_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/auth/signup
* Method: **POST**
* Body: [Login DTO](#dto-login)

#### Returns
```json
{
    "_id": "5aaaad170724cf32akjdi7ea",
    "email": "pudi17@hotmail.com",
    "email_verified": false
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

##### SignUp Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "username or password is invalid",
	"errors": [
		""
	]
}
```
### &gt;_Login_ [To Top ^](#summary)
Login types: __Admin__, __User__
* Slug: [_base_url_] /v1/auth/{_type_}/login
* Method: **POST**
* Body: [Login DTO](#dto-login)

#### Returns
```json
{
	"access_token": "eyJ0eXAiOiJKV1QiLCJWTNOdyJ9.eyJpc3MiOiJod29yZCJ9.TfZ8zvEBLVv8foqvQXAzlx1yFA0s12K",
	"id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI.eyJodHRwczovL2ZpbmFs.4j4gejazpLxosEJc7rmEk_r4uSYECGC",
	"token_type": "Bearer",
	"expires_in": 86400
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

##### Login Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "Wrong email or password.",
	"errors": [
		""
	]
}
```

### &gt;_Logout_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/auth/logout
* Method: **POST**
* Headers: Bearer token
* Body: _NONE_

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

### &gt;_Reset password_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/auth/reset-password
* Method: **POST**
* Body: [Login DTO](#dto-login)

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>
<br/>
<br/>


## Room [To Top ^](#summary)
### Base slug
[_base_url_] /v1/room

### Model: Room

parameter       |Type           |required
----------------|---------------|:--------:
id              |String         |Yes
name            |String         |Yes
description     |String         |Yes
location        |String         |Yes


### DTO: Room

parameter       |Type           |required
----------------|---------------|:--------:
name            |String         |Yes
description     |String         |Yes
location        |String         |Yes

### DTO: Room for AR

parameter       |Type                              |required
----------------|----------------------------------|:--------:
roomLocation    |Vector3                           |Yes
roomInfo        |[Room](#model-room)                |Yes&ast;
itemList        |List - [DataItem](#model-data-item) |No

&ast; Room id is required, rest optional

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/room
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
		"id": "b94fa41d-ssss-4c4c-9c42-b5d654c5c0b3",
		"name": "Hospital R247",
		"description": "Hosiptal scenario room",
		"location": "Chirec"
	},
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Get By Id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/room/byId/{_room-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "id": "b94fa41d-ssss-4c4c-9c42-b5d654c5c0b3",
    "name": "Hospital R247",
    "description": "Hosiptal scenario room",
    "location": "Chirec"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/room
* Method: **POST**
* Body: [Room Dto](#dto-room)

#### Returns
```json
{
    "id": "b94fa41d-ssss-4c4c-9c42-b5d654c5c0b3",
    "name": "Hospital R247",
    "description": "Hosiptal scenario room",
    "location": "Chirec"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```

### &gt;_Update_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/room
* Method: **PUT**
* Body: [Room](#model-room)

#### Returns
```json
{
    "id": "b94fa41d-ssss-4c4c-9c42-b5d654c5c0b3",
    "name": "Hospital R247",
    "description": "Hosiptal scenario room",
    "location": "Chirec"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/room/{_room-id_}
* Method: **PUT**
* Body: [Room](#model-room)

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```

<h3 id="room-ws">Web Sockets</h3>

### &gt;_Room for AR_ [To Top ^](#summary)

* Request Channel: [_base_url_] /app/room/{_private_user_channel_}/{_room-id_}
* Response Channel: [_base_url_] /topic/room/{_private_user_channel_}
* Body: [RoomForAR dto](#dto-room-for-ar)

#### Returns
```json
{
    "roomLocation": {
        "y":0.0021007359027862549,
        "x":0.024585127830505371,
        "z":0.015472851693630219
    },
    "itemList": [
        {
            "itemId":"tap_3",
            "name":"Jupiler tap",
            "location": {
                "y":0,
                "x":0,
                "z":0
            },
            "id":"7d077626-eaad-46e7-9bd0-e1cd394fdcce",
            "roomId":"88d10d30-b5c2-4555-b15c-416c6f7d0935"
        },
        {
            ...
        }
    ],
    "roomInfo": {
        "description":"Example scenario for smart cafe. Where bartenders and customer can see current stock",
        "name":"Cafe scenario",
        "id":"88d10d30-b5c2-4555-b15c-416c6f7d0935",
        "itemList": [
            {
                "itemId":"tap_3",
                "name":"Jupiler tap",
                "location": {
                    "y":0,
                    "x":0,
                    "z":0
                },
                "id":"7d077626-eaad-46e7-9bd0-e1cd394fdcce",
                "roomId":"88d10d30-b5c2-4555-b15c-416c6f7d0935"
            },
            {
                ...
            }
        ]
    }
}


```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```
<br/>
<br/>


## Beacon [To Top ^](#summary)
### Base slug
[_base_url_] /v1/beacon

### Model: Beacon

parameter           |Type           |required
--------------------|---------------|:--------:
id                  |String         |Yes
roomId              |String         |Yes
name                |String         |Yes
description         |String         |Yes
major               |Long           |Yes
minor               |Long           |Yes
calibrationFactor   |Long           |Yes
lastUpdated         |Long           |Yes

### DTO: Beacon

parameter           |Type           |required
--------------------|---------------|:--------:
roomId              |String         |Yes
name                |String         |Yes
description         |String         |Yes
major               |Long           |Yes
minor               |Long           |Yes
calibrationFactor   |Long           |Yes
lastUpdated         |Long           |Yes

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/beacon
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
        "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
        "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
        "name": "beacon_1_2",
        "description": "Lokaal a201 pos.2",
        "major": 1,
        "minor": 2,
        "calibrationFactor": 61,
        "lastUpdated": 1524669288
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/beacon
* Method: **POST**
* Body: [Beacon Dto](#dto-beacon)

#### Returns
```json
{
    "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
    "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
    "name": "beacon_1_2",
    "description": "Lokaal a201 pos.2",
    "major": 1,
    "minor": 2,
    "calibrationFactor": 61,
    "lastUpdated": 1524669288
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/beacon
* Method: **PUT**
* Body: [Beacon](#model-beacon)

#### Returns
```json
{
    "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
    "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
    "name": "beacon_1_2",
    "description": "Lokaal a201 pos.2",
    "major": 1,
    "minor": 2,
    "calibrationFactor": 61,
    "lastUpdated": 1524669288
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/beacon/{_beacon-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```

<h3 id="beacon-ws">Web Sockets</h3>

### &gt;_Get All_ [To Top ^](#summary)

* Request Channel: [_base_url_] /app/beacon/{_private_user_channel_} 
* Response Channel: [_base_url_] /topic/beacon/{_private_user_channel_}
* Body: [Beacon](#model-beacon)

#### Returns
```json
[
	{
        "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
        "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
        "name": "beacon_1_2",
        "description": "Lokaal a201 pos.2",
        "major": 1,
        "minor": 2,
        "calibrationFactor": 61,
        "lastUpdated": 1524669288
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Create_ [To Top ^](#summary)

* Request Channel: [_base_url_] /app/beacon/{_private_user_channel_}/calibrate
* Response Channel: [_base_url_] /topic/beacon/{_private_user_channel_}/calibrate
* Body: [Beacon](#model-beacon)

#### Returns
```json
{
    "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
    "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
    "name": "beacon_1_2",
    "description": "Lokaal a201 pos.2",
    "major": 1,
    "minor": 2,
    "calibrationFactor": 61,
    "lastUpdated": 1524669288
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```

### &gt;_Get by Major &amp; Minor_ [To Top ^](#summary)

* Request Channel: [_base_url_] /app/beacon/{_private_user_channel_}/getByMajorMinor/{_major_}/{_minor_}
* Response Channel: [_base_url_] /topic/beacon/{_private_user_channel_}/getByMajorMinor
* Body: _NONE_

#### Returns
```json
{
    "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
    "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
    "name": "beacon_1_2",
    "description": "Lokaal a201 pos.2",
    "major": 1,
    "minor": 2,
    "calibrationFactor": 61,
    "lastUpdated": 1524669288
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### TooManyReturnValuesWebSocket Exception
* Status code: 300
```json
{
	"status": "MULTIPLE_CHOICES",
	"message": "Too Many Values",
	"errors": [
		""
	]
}
```

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Not Found",
	"errors": [
		""
	]
}
```

### &gt;_Calibrate_ [To Top ^](#summary)

* Request Channel: [_base_url_] /app/beacon/{_private_user_channel_}/calibrate
* Response Channel: [_base_url_] /topic/beacon/{_private_user_channel_}/calibrate
* Body: [Beacon](#model-beacon)

#### Returns
```json
{
    "id": "b82881db-eeee-44c4-a76c-a8c9a1cc282c",
    "roomId": "b94fa41d-aaaa-4c4c-9c42-b5d654c5c0b3",
    "name": "beacon_1_2",
    "description": "Lokaal a201 pos.2",
    "major": 1,
    "minor": 2,
    "calibrationFactor": 61,
    "lastUpdated": 1524669288
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Not Found",
	"errors": [
		""
	]
}
```
<br/>
<br/>

## DataItem [To Top ^](#summary)
### Base slug
[_base_url_] /v1/beacon

### Model: DataItem

parameter           |Type           |required
--------------------|---------------|:--------:
id                  |String         |Yes
itemId              |String         |Yes
name                |String         |Yes
location            |Vector3        |Yes
roomId              |String         |Yes

### DTO: DataItem

parameter           |Type           |required
--------------------|---------------|:--------:
itemId              |String         |Yes
name                |String         |Yes
location            |Vector3        |Yes
roomId              |String         |Yes

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
        "id": "b407f8ac-zzzz-4f93-81a9-1d34b8442333",
        "itemId": "arm_aparatus_21",
        "name": "Arms 21",
        "location": {
            "x": 0.0,
            "y": 0.0,
            "z": 0.0
        },
        "roomId": "d5182fb3-zzzz-4a9e-994d-c004b003ebe4"
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Get by Room id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem/byRoomId/{_room-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
        "id": "b407f8ac-zzzz-4f93-81a9-1d34b8442333",
        "itemId": "arm_aparatus_21",
        "name": "Arms 21",
        "location": {
            "x": 0.0,
            "y": 0.0,
            "z": 0.0
        },
        "roomId": "d5182fb3-zzzz-4a9e-994d-c004b003ebe4"
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Get by id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem/byId/{_dataItem-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "id": "b407f8ac-zzzz-4f93-81a9-1d34b8442333",
    "itemId": "arm_aparatus_21",
    "name": "Arms 21",
    "location": {
        "x": 0.0,
        "y": 0.0,
        "z": 0.0
    },
    "roomId": "d5182fb3-zzzz-4a9e-994d-c004b003ebe4"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>


### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem
* Method: **POST**
* Body: [DataItem Dto](#dto-dataitem)

#### Returns
```json
{
    "id": "b407f8ac-zzzz-4f93-81a9-1d34b8442333",
    "itemId": "arm_aparatus_21",
    "name": "Arms 21",
    "location": {
        "x": 0.0,
        "y": 0.0,
        "z": 0.0
    },
    "roomId": "d5182fb3-zzzz-4a9e-994d-c004b003ebe4"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem
* Method: **PUT**
* Body: [DataItem](#model-dataItem)

#### Returns
```json
{
    "id": "b407f8ac-zzzz-4f93-81a9-1d34b8442333",
    "itemId": "arm_aparatus_21",
    "name": "Arms 21",
    "location": {
        "x": 0.0,
        "y": 0.0,
        "z": 0.0
    },
    "roomId": "d5182fb3-zzzz-4a9e-994d-c004b003ebe4"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem/{_dataItem-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```
<br/>
<br/>

## DataSource [To Top ^](#summary)
### Base slug
[_base_url_] /v1/dataSource

### Model info
This model servers for selecting data sources (from Web Socket endpoints) and convert the incoming data to the used DataLog convention.

### Model: DataSource

parameter           |Type                                               |required
--------------------|---------------------------------------------------|:--------:
id                  |String                                             |Yes
url                 |String                                             |Yes
destinations        |List - [DataDestination](#model-datadestination)   |Yes

### Model: DataDestination

parameter           |Type                                                           |required
--------------------|---------------------------------------------------------------|:--------:
destination         |String                                                         |Yes
conversionScheme    |List - [ConversionSchemeEntry](#model-conversionschemeentry)   |Yes

### Model: ConversionSchemeEntry

parameter           |Type       |required
--------------------|-----------|:--------:
incomingDataKey     |String     |Yes
dataLogData         |Object*    |Yes

&ast; Should be either:
* String: For dataItemId
* [InformationConversionDto](#dto-informationconversion): To display the information in AR


### DTO: InformationConversion

parameter   |Type       |required
------------|-----------|:--------:
name        |String     |Yes
index       |Long       |Yes

Name: should be the wanted name to display in AR
Index: should be the order of the selected item

### DTO: DataSource

parameter           |Type                                               |required
--------------------|---------------------------------------------------|:--------:
url                 |String                                             |Yes
destinations        |List - [DataDestination](#model-datadestination)   |Yes

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
        "id": "94145f76-dddd-4277-b1b9-d70f8016d83a",
        "url": "ws://127.0.0.1:9000/managerWS",
        "destinations": [
            {
                "destination": "/topic/echo/ws",
                "conversionScheme": [
                    {
                        "incomingDataKey": "id",
                        "dataLogData": "item_id"
                    },
                    {
                        "incomingDataKey": "type",
                        "dataLogData": {
                            "name": "Kind",
                            "index": 1
                        }
                    },
                    {
                        ...
                    }
                ]
            }
        ]
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Get by id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataSource/byId/{_dataSource-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "id": "94145f76-dddd-4277-b1b9-d70f8016d83a",
    "url": "ws://127.0.0.1:9000/managerWS",
    "destinations": [
        {
            "destination": "/topic/echo/ws",
            "conversionScheme": [
                {
                    "incomingDataKey": "id",
                    "dataLogData": "item_id"
                },
                {
                    "incomingDataKey": "type",
                    "dataLogData": {
                        "name": "Kind",
                        "index": 1
                    }
                },
                {
                    ...
                }
            ]
        }
    ]
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataSource
* Method: **POST**
* Body: [DataSource Dto](#dto-datasource)

#### Returns
```json
{
    "id": "94145f76-dddd-4277-b1b9-d70f8016d83a",
    "url": "ws://127.0.0.1:9000/managerWS",
    "destinations": [
        {
            "destination": "/topic/echo/ws",
            "conversionScheme": [
                {
                    "incomingDataKey": "id",
                    "dataLogData": "item_id"
                },
                {
                    "incomingDataKey": "type",
                    "dataLogData": {
                        "name": "Kind",
                        "index": 1
                    }
                },
                {
                    ...
                }
            ]
        }
    ]
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataSource
* Method: **PUT**
* Body: [DataSource](#model-datasource)

#### Returns
```json
{
    "id": "94145f76-dddd-4277-b1b9-d70f8016d83a",
    "url": "ws://127.0.0.1:9000/managerWS",
    "destinations": [
        {
            "destination": "/topic/echo/ws",
            "conversionScheme": [
                {
                    "incomingDataKey": "id",
                    "dataLogData": "item_id"
                },
                {
                    "incomingDataKey": "type",
                    "dataLogData": {
                        "name": "Kind",
                        "index": 1
                    }
                },
                {
                    ...
                }
            ]
        }
    ]
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataItem/{_dataSource-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```
<br/>
<br/>

## DataLog [To Top ^](#summary)
### Base slug
[_base_url_] /v1/dataLog

### Model: DataLog

parameter           |Type                                       |required
--------------------|-------------------------------------------|:--------:
id                  |String                                     |Yes
itemId              |String                                     |Yes
information         |List - [Information](#model-information)   |Yes
timestamp	        |Long					                    |Yes

### Model: Information


parameter   |Type       |required
------------|-----------|:--------:
name        |String     |Yes
data        |String     |Yes
index       |Long       |Yes

### DTO: DataLog

parameter           |Type                                       |required
--------------------|-------------------------------------------|:--------:
itemId              |String                                     |Yes
information         |List - [Information](#model-information)   |Yes
timestamp	        |Long					                    |Yes

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataLog
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
        "id": "0062359f-llll-4bcf-8c63-b14816b88727",
        "itemId": "tap_3",
        "information": [
            {
                "data": "3.02",
                "name": "Temp",
                "index": 1
            },
            {
                "data": "11.535",
                "name": "Pressure",
                "index": 2
            },
            {
                "data": "40",
                "name": "Litres",
                "index": 3
            }
        ],
        "timestamp": 1525270179
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Get By Id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataLog/byId/{_dataLog-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "id": "0062359f-llll-4bcf-8c63-b14816b88727",
    "itemId": "tap_3",
    "information": [
        {
            "data": "3.02",
            "name": "Temp",
            "index": 1
        },
        {
            "data": "11.535",
            "name": "Pressure",
            "index": 2
        },
        {
            "data": "40",
            "name": "Litres",
            "index": 3
        }
    ],
    "timestamp": 1525270179
}
```
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

### &gt;_Get By Item Id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataLog/byItemId/{_item-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
		"id": "0062359f-llll-4bcf-8c63-b14816b88727",
		"itemId": "tap_3",
		"information": [
			{
				"data": "3.02",
				"name": "Temp",
				"index": 1
			},
			{
				"data": "11.535",
				"name": "Pressure",
				"index": 2
			},
			{
				"data": "40",
				"name": "Litres",
				"index": 3
			}
		],
		"timestamp": 1525270179
	},
	{
		"id": "008d6c1f-llll-44a9-b59b-38970488503f",
		"itemId": "tap_3",
		...
	}
]
```
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/dataLog
* Method: **POST**
* Body: [DataLog Dto](#model-datalog)

#### Returns
```json
{
    "id": "0062359f-llll-4bcf-8c63-b14816b88727",
    "itemId": "tap_3",
    "information": [
        {
            "data": "3.02",
            "name": "Temp",
            "index": 1
        },
        {
            "data": "11.535",
            "name": "Pressure",
            "index": 2
        },
        {
            "data": "40",
            "name": "Litres",
            "index": 3
        }
    ],
    "timestamp": 1525270179
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```

<h3 id="datalog-ws">Web Sockets</h3>

### &gt;_Get All_ [To Top ^](#summary)

* Request Channel: _NONE_
* Response Channel: [_base_url_] /topic/dataLog
* Body: [DataLog](#model-datalog)

#### Returns
```json
{
    "id": "0062359f-llll-4bcf-8c63-b14816b88727",
    "itemId": "tap_3",
    "information": [
        {
            "data": "3.02",
            "name": "Temp",
            "index": 1
        },
        {
            "data": "11.535",
            "name": "Pressure",
            "index": 2
        },
        {
            "data": "40",
            "name": "Litres",
            "index": 3
        }
    ],
    "timestamp": 1525270179
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

<br/>
<br/>

## DataItemRequest [To Top ^](#summary)
### Base slug
[_base_url_] /v1/request

### Model: DataItemRequest

parameter       |Type           |required
----------------|---------------|:--------:
id              |String         |Yes
beaconId        |String         |Yes
dataItemName    |String         |Yes
requester       |String&ast;    |Yes

&ast; Should be the requester's email. Administration set to easily send mails.

### DTO: DataItemRequest

parameter       |Type           |required
----------------|---------------|:--------:
beaconId        |String         |Yes
dataItemName    |String         |Yes
requester       |String&ast;    |Yes

&ast; Should be the requester's email. Administration set to easily send mails.

### DTO: DataItemRequestForAdmin

parameter       |Type                       |required
----------------|---------------------------|:--------:
id              |String                     |Yes
beaconId        |String                     |Yes
dataItemName    |String                     |Yes
requester       |String&ast;                |Yes
beacon          |[Beacon](#model-beacon)    |Yes
room            |[Room](#model-room)        |Yes

&ast; Should be the requester's email. Administration set to easily send mails.

### API
### &gt;_Get All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/request
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
		"id": "f82816dc-dddd-42e5-bf47-8d3f81217cfa",
		"beaconId": "d228384b-aaaa-4fa4-8f3a-c1c4d17997ac",
		"dataItemName": "pom",
		"requester": "lud.marcha@pom.be"
	},
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Get For Admin_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/request/getForAdmin
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
        "id": "f82816dc-dddd-42e5-bf47-8d3f81217cfa",
        "beaconId": "d228384b-zzzz-4fa4-8f3a-c1c4d17997ac",
        "dataItemName": "pom",
        "requester": "lud.marcha@pom.be",
        "beacon": {
            "id": "d228384b-zzzz-4fa4-8f3a-c1c4d17997ac",
            "roomId": "164fe621-ffff-4b18-87d0-7d56d56a6640",
            "name": "beacon_1_1",
            "description": "Lokaal a201 pos.1",
            "major": 1,
            "minor": 1,
            "calibrationFactor": 61,
            "lastUpdated": 1524669288
        },
        "room": {
            "id": "164fe621-ffff-4b18-87d0-7d56d56a6640",
            "name": "Hospital R246",
            "description": "Hosiptal scenario room",
            "location": "Chirec"
        }
    },
	{
	    ...
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>


### &gt;_Get by id_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/request/byId/{_request-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "id": "f82816dc-dddd-42e5-bf47-8d3f81217cfa",
    "beaconId": "d228384b-aaaa-4fa4-8f3a-c1c4d17997ac",
    "dataItemName": "pom",
    "requester": "lud.marcha@pom.be"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

### &gt;_Create_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/request
* Method: **POST**
* Body: [Request Dto](#dto-dataitemrequest)

#### Returns
```json
{
    "id": "f82816dc-dddd-42e5-bf47-8d3f81217cfa",
    "beaconId": "d228384b-aaaa-4fa4-8f3a-c1c4d17997ac",
    "dataItemName": "pom",
    "requester": "lud.marcha@pom.be"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### ItemNotCreated Exception
* Status code: 409
```json
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/reauest
* Method: **PUT**
* Body: [Request](#model-dataitemrequest)

#### Returns
```json
{
    "id": "f82816dc-dddd-42e5-bf47-8d3f81217cfa",
    "beaconId": "d228384b-aaaa-4fa4-8f3a-c1c4d17997ac",
    "dataItemName": "pom",
    "requester": "lud.marcha@pom.be"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/request/{_request-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```json
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```json
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

##### CustomNotFound Exception
* Status code: 404
```json
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```
<br/>
<br/>

## Debug [To Top ^](#summary)
### Base slug
[_base_url_] /v1/debug

### Purpose
This controller only serves to debug. IT does __two__ important things.
1. Generate the example scenarios that will be used for presenting the project.
2. Echo for Web Socket to emulate a Web Socket endpoint.<br/>
(Searched online to find a legit endpoint to use, instead of an echo with a Javascript script, but no endpoint was found that sent data at an acceptable rate.)

### API
### &gt;_Generate All_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/debug/generate
* Method: **GET**
* Body: _NONE_

#### Returns
```json
[
	{
	    "sport_scenario id": "d228384b-aaaa-4fa4-8f3a-c1c4d17997ac"
	},
    {
        "cafe_scenario id": "d228384b-ssss-4fa4-8f3a-c1c4d17997ac"
    },
    {
        "hospital_scenario id": "d228384b-dddd-4fa4-8f3a-c1c4d17997ac"
    }
]
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Generate Sport_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/debug/generate/sport
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "sport_scenario id": "d228384b-aaaa-4fa4-8f3a-c1c4d17997ac"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Generate Cafe_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/debug/generate/cafe
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "cafe_scenario id": "d228384b-ssss-4fa4-8f3a-c1c4d17997ac"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

### &gt;_Generate Hospital_ [To Top ^](#summary)
* Slug: [_base_url_] /v1/debug/generate/hospital
* Method: **GET**
* Body: _NONE_

#### Returns
```json
{
    "hospital_scenario id": "d228384b-dddd-4fa4-8f3a-c1c4d17997ac"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

<h3 id="debug-ws">Web Sockets</h3>

### &gt;_Get All_ [To Top ^](#summary)

* Request Channel: [_base_url_] /app/echo/{_channel_}&ast;
* Response Channel: [_base_url_] /topic/echo/{_channel_}
* Body: JSON String that needs to be echoed back

&ast; Channel where echo should be sent too. Doesn't need to be a private channel

#### Returns (Example)
```json
{
	"user": {
		"info": {
			"name": "Jeff",
			"age": 34,
			"membership": {
				"from": 1463060368544,
				"to": 1549031968544
			}
		},
		"stats": {
			"time": 28,
			"kcal_burned": 70,
			"heart_rate": 113
		}
	},
	"equipment_id": "treadmill_21"
}
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

<br/>
<br/>

## System Monitoring [To Top ^](#summary)
This only exists as a Web Socket endpoint. 

This endpoint broadcasts Server information every 5 seconds. This will primarily be used by the Administration panel.

<h3 id="sysMon-ws">Web Sockets</h3>

### &gt;_Get system information_ [To Top ^](#summary)

* Request Channel: _NONE_
* Response Channel: [_base_url_] /topic/monitoring
* Body: _NONE_

#### Returns (Example)
```json
{
	"memory": {
		"totalMemory": "2062584 kB",
		"freeMemory": "627120 kB",
		"availableMemory": "1618192 kB"
	},
	"cpu": {
		"name": "ARMv7 Processor rev 1 (v7l)",
		"maxClockSpeed": "1800000",
		"currentClockSpeed": "216000",
		"temperature": "41",
		"loadPercentage": "12.00"
	}
}
```

#### Errors
No errors possible.<br/>
Data kan be `null` as some functions are hardware specific.

<br/>
<br/>

## Notifications [To Top ^](#summary)
This only exists as a Web Socket endpoint. 

This endpoint broadcasts notifications when the service is called.<br/>
While the ChangeListener can be set to listen for changes in the database and send those changes on specific channels, <br/>
this endpoint can be set to send any kind of data that doesn't have to come from the Database.
The send data can also be chosen on the moment of implementation.

<h3 id="notifications-ws">Web Sockets</h3>

### &gt;_Request notification_ [To Top ^](#summary)

* Request Channel: _NONE_
* Response Channel: [_base_url_] /topic/notification/request
* Body: _NONE_

#### Returns (Example)
This is set up to only send the number of request there currently are. Mainly serves as example.
```
  2
```

#### Errors
No errors possible.

<br/>
<br/>