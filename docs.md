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

***********************************************************************************************************************
## Auth0 management
### Base slug
[base_url]/v1/management/user
### Model
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>
parameter       |required  
----------------|:--------:
client_id       |Yes
connection      |Yes 
password        |Yes
verify_password |Yes
username        |Yes
email           |Yes
Verify_email    |Yes 
identities      |Yes
email_verified  |No 
phone_number    |No
phone_verified  |No
user_id         |No
picture         |No
name            |No
nickname        |No
given_name      |No
family_name     |No
created_at      |No
updated_at      |No
app_metadata    |No
user_metadata   |No
multifactor     |No
last_ip         |No
last_login      |No
logins_count    |No
blocked         |No

<h3 id="auth0-management-api">API</h3>

Slug        | Method | Body        
------------|:------:|------
base_slug   | GET    | None 

#####Returns
```
[
	{
		"email": "pudi1711@hotmail.com",
		"email_verified": false,
		"user_id": "auth0|5aaaad170724cf32a028e7ea",
		"picture": "https://s.gravatar.com/avatar/b798048f060d1a54df7a0a92778b8a55?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fpu.png",
		"name": "pudi1711@hotmail.com",
		"nickname": "pudi1711",
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

Slug        | Method | Body        
------------|:------:|------
base_slug   | POST   | User 

#####Returns
```
{
    "email": "pudi1711@hotmail.com",
    "email_verified": false,
    "user_id": "auth0|5aaaad170724cf32akjdi7ea",
    "picture": "https://s.gravatar.com/avatar/b798048f060d1a54df7a0a92778b8a55?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fpu.png",
    "name": "pudi1711@hotmail.com",
    "nickname": "pudi1711",
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

Slug        | Method | Body        
------------|:------:|------
base_slug   | PUT    | User 

#####Returns
```
{
    "email": "pudi1711@hotmail.com",
    "email_verified": false,
    "user_id": "auth0|5aaaad170724cf32akjdi7ea",
    "picture": "https://s.gravatar.com/avatar/b798048f060d1a54df7a0a92778b8a55?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fpu.png",
    "name": "pudi1711@hotmail.com",
    "nickname": "pudi1711",
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

Slug        | Method | Body        
------------|:------:|------
base_slug   | DELETE | User 

#####Returns
```
{
    "email": "pudi1711@hotmail.com",
    "email_verified": false,
    "user_id": "auth0|5aaaad170724cf32akjdi7ea",
    "picture": "https://s.gravatar.com/avatar/b798048f060d1a54df7a0a92778b8a55?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fpu.png",
    "name": "pudi1711@hotmail.com",
    "nickname": "pudi1711",
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
For base errors check here: [Recurring errors](#recurring-errors).<br/>


## Authentication 
### Model
### DTO
<h3 id="authentication-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

## Room
### Model
### DTO
<h3 id="room-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
<h3 id="room-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

## Beacon
### Model
### DTO
<h3 id="beacon-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
<h3 id="beacon-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

## Data Items
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

## Data Sources
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

## DataLog
### Model
### DTO
<h3 id="datal-og-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
<h3 id="data-log-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

## Data Item Request
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>


## Debug
### Model
### DTO
<h3 id="debug-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>
<h3 id="debug-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors).<br/>

