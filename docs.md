# RAM - API &amp; WS documentation
Documentation is split between API and Web Sockets endpoints. Link to each group can be found in the summary.<br/>
For this backend application to fully work, a working copy of _security.properties_ will be needed in the resources.

## Summary
1. [Working with websockets](#web-socket-basics)
1. [Recurring errors](#recurring-errors)
1. [Auth0 management](#auth0-management)
2. [Authentication](#authentication)
7. [Room](#room)
3. [Beacon](#beacon)
4. [DataItem](#data-item)
6. [DataSource](#data-source)
5. [DataLog](#data-log)
5. [DataItemRequest](#data-item-request)
8. [Debug](#debug)

***********************************************************************************************************************
## Web Socket basics
Web sockets currently need no credentials to be used. This may changed as the backend evolves.

### Summary of models using Web Sockets
1. [Beacon](#beacon-ws)
2. [Room](#room-ws)
3. [DataLog](#datalog-ws)
4. [Debug](#debug-ws)

***********************************************************************************************************************
## Recurring errors
### 400
occurs when there is no existing slug to the asked method

Example:
```
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
```
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
```
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
## Auth0 management
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
### &gt;_Get All_
* Slug: [_base_url_] /v1/management/user
* Method: **GET**
* Body: [User](#model-user)

#### Returns
```
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

### &gt;_Create_
* Slug: [_base_url_] /v1/management/user
* Method: **POST**
* Body: [User](#model-user)

#### Returns
```
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

### &gt;_Edit_
* Slug: [_base_url_] /v1/management/user
* Method: **PUT**
* Body: [User](#model-user)

#### Returns
```
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

### &gt;_Delete_
* Slug: [_base_url_] /v1/management/user
* Method: **DELETE**
* Body: [User](#model-user)

#### Returns
```
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

## Authentication 
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
### &gt;_Sign up_
* Slug: [_base_url_] /v1/auth/signup
* Method: **POST**
* Body: [Login DTO](#dto-login)

#### Returns
```
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
```
{
	"status": "BAD_REQUEST",
	"message": "username or password is invalid",
	"errors": [
		""
	]
}
```
### &gt;_Login_
Login types: __Admin__, __User__
* Slug: [_base_url_] /v1/auth/{_type_}/login
* Method: **POST**
* Body: [Login DTO](#dto-login)

#### Returns
```
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
```
{
	"status": "BAD_REQUEST",
	"message": "Wrong email or password.",
	"errors": [
		""
	]
}
```

### &gt;_Logout_
* Slug: [_base_url_] /v1/auth/logout
* Method: **POST**
* Headers: Bearer token
* Body: _NONE_

#### Returns
```
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

### &gt;_Reset password_
* Slug: [_base_url_] /v1/auth/reset-password
* Method: **POST**
* Body: [Login DTO](#dto-login)

#### Returns
```
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>
<br/>
<br/>


## Room
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
### &gt;_Get All_
* Slug: [_base_url_] /v1/room
* Method: **GET**
* Body: _NONE_

#### Returns
```
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

### &gt;_Get By Id_
* Slug: [_base_url_] /v1/room/byId/{_room-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```
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
```
{
	"status": "NOT_FOUND",
	"message": "Item with id: _ID_ not found.",
	"errors": [
		""
	]
}
```

### &gt;_Create_
* Slug: [_base_url_] /v1/room
* Method: **POST**
* Body: [Room Dto](#dto-room)

#### Returns
```
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
```
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```

### &gt;_Update_
* Slug: [_base_url_] /v1/room
* Method: **PUT**
* Body: [Room](#model-room)

#### Returns
```
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
```
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_
* Slug: [_base_url_] /v1/room/{_room-id_}
* Method: **PUT**
* Body: [Room](#model-room)

#### Returns
```
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### CustomNotFound Exception
* Status code: 404
```
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```

<h3 id="room-ws">Web Sockets</h3>
### &gt;_Room for AR_

* Request Channel: [_base_url_] /room/{_private user channel_}/{_room-id_}
* Response Channel: [_base_url_] /topic/room/{_private user channel_}
* Body: [RoomForAR dto](#dto-room-for-ar)

#### Returns
```
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
```
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
```
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


## Beacon
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
### &gt;_Get All_
* Slug: [_base_url_] /v1/beacon
* Method: **GET**
* Body: _NONE_

#### Returns
```
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

### &gt;_Create_
* Slug: [_base_url_] /v1/beacon
* Method: **POST**
* Body: [Beacon Dto](#dto-beacon)

#### Returns
```
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
```
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_
* Slug: [_base_url_] /v1/beacon
* Method: **PUT**
* Body: [Beacon](#model-beacon)

#### Returns
```
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
```
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_
* Slug: [_base_url_] /v1/beacon/{_beacon-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```
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
```
{
	"status": "NOT_FOUND",
	"message": "Item with id: _item-id_: Not Found",
	"errors": [
		""
	]
}
```

<h3 id="beacon-ws">Web Sockets</h3>

### &gt;_Get All_

* Request Channel: [_base_url_] /beacon/
* Response Channel: [_base_url_] /topic/beacon
* Body: [Beacon](#model-beacon)

#### Returns
```
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

### &gt;_Create_

* Request Channel: [_base_url_] /beacon/{_private user channel_}/calibrate
* Response Channel: [_base_url_] /topic/beacon/{_private user channel_}/calibrate
* Body: [Beacon](#model-beacon)

#### Returns
```
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
```
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```

### &gt;_Get by Major &amp; Minor_

* Request Channel: [_base_url_] /beacon/{_private user channel_}/getByMajorMinor/{_major_}/{_minor_}
* Response Channel: [_base_url_] /topic/beacon/{_private user channel_}/getByMajorMinor
* Body: _NONE_

#### Returns
```
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
```
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
```
{
	"status": "NOT_FOUND",
	"message": "Not Found",
	"errors": [
		""
	]
}
```

### &gt;_Calibrate_

* Request Channel: [_base_url_] /beacon/{_private user channel_}/calibrate
* Response Channel: [_base_url_] /topic/beacon/{_private user channel_}/calibrate
* Body: [Beacon](#model-beacon)

#### Returns
```
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
```
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

## Data Items
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
### &gt;_Get All_
* Slug: [_base_url_] /v1/dataItem
* Method: **GET**
* Body: _NONE_

#### Returns
```
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

### &gt;_Get by Room id_
* Slug: [_base_url_] /v1/dataItem/byRoomId/{_room-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```
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

### &gt;_Get by id_
* Slug: [_base_url_] /v1/dataItem/byId/{_dataItem-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```
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
```
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


### &gt;_Create_
* Slug: [_base_url_] /v1/dataItem
* Method: **POST**
* Body: [DataItem Dto](#dto-dataitem)

#### Returns
```
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
```
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_
* Slug: [_base_url_] /v1/dataItem
* Method: **PUT**
* Body: [DataItem](#model-dataItem)

#### Returns
```
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
```
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_
* Slug: [_base_url_] /v1/dataItem/{_dataItem-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```
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
```
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

## Data Sources
### Base slug
[_base_url_] /v1/dataSource

### Model info
This model servers for selecting data sources (from Web Socket endpoints) and convert the incoming data to the used DataLog convention.

### Model: DataSource
    
parameter           |Type                                               |required  
--------------------|---------------------------------------------------|:--------:
id                  |String                                             |Yes
url                 |String                                             |Yes
destinations        |List - [DataDestination](#model-dataDestination)   |Yes

### Model: DataDestination
    
parameter           |Type                                                           |required  
--------------------|---------------------------------------------------------------|:--------:
destination         |String                                                         |Yes
conversionScheme    |List - [ConversionSchemeEntry](#model-conversionSchemeEntry)   |Yes

### Model: DataDestination

parameter           |Type       |required  
--------------------|-----------|:--------:
incomingDataKey     |String     |Yes
dataLogData         |Object*    |Yes

&ast; Should be either:
* String: For dataItemId
* [InformationConversionDto](#dto-informationConversionDto): To display the information in AR


### DTO: InformationConversionDto

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
destinations        |List - [DataDestination](#model-dataDestination)   |Yes

### API
### &gt;_Get All_
* Slug: [_base_url_] /v1/dataItem
* Method: **GET**
* Body: _NONE_

#### Returns
```
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

### &gt;_Get by id_
* Slug: [_base_url_] /v1/dataSource/byId/{_dataSource-id_}
* Method: **GET**
* Body: _NONE_

#### Returns
```
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
```
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


### &gt;_Create_
* Slug: [_base_url_] /v1/dataSource
* Method: **POST**
* Body: [DataSource Dto](#dto-datasource)

#### Returns
```
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
```
{
	"status": "CONFLICT",
	"message": "Parameter Conflicts",
	"errors": [
		""
	]
}
```


### &gt;_Update_
* Slug: [_base_url_] /v1/dataSource
* Method: **PUT**
* Body: [DataSource](#model-datasource)

#### Returns
```
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
```
{
	"status": "BAD_REQUEST",
	"message": "id parameter missing from request",
	"errors": [
		""
	]
}
```

### &gt;_Delete_
* Slug: [_base_url_] /v1/dataItem/{_dataSource-id_}
* Method: **DELETE**
* Body: _NONE_

#### Returns
```
{ }
```

#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

##### MissingId Exception
* Status code: 400
```
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
```
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

## DataLog
### Model
### DTO
<h3 id="datal-og-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<h3 id="data-log-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<br/>
<br/>

## Data Item Request
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<br/>
<br/>

## Debug
### Model
### DTO
<h3 id="debug-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<h3 id="debug-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

