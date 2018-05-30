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
[_base_url_] /v1/management/user

### Model: User
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

### API 
#### Get All
* Slug: [_base_url_] /v1/management/user
* Method: **GET**
* Body: [User](#model-user)

##### Returns
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
##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

#### Create
* Slug: [_base_url_] /v1/management/user
* Method: **POST**
* Body: [User](#model-user)

##### Returns
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
##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

#### Edit
* Slug: [_base_url_] /v1/management/user
* Method: **PUT**
* Body: [User](#model-user)

##### Returns
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
##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

#### Delete
* Slug: [_base_url_] /v1/management/user
* Method: **DELETE**
* Body: [User](#model-user)

##### Returns
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
##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
Other possible errors: This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

## Authentication 
### Base slug
[_base_url_] /v1/auth
### Model: Create
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

parameter       |required  
----------------|:--------:
_id             |Yes
email           |Yes
email_verified  |Yes

### Model Token holder
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

parameter       |required  
----------------|:--------:
access_token    |Yes
id_token        |Yes 
refresh_token   |Yes
token_type      |Yes
expires_in      |Yes

### DTO: Login 

parameter       |required  
----------------|:--------:
email           |Yes
password        |Yes 

### API
#### __Sign up__
* Slug: [_base_url_] /v1/auth/signup
* Method: **POST**
* Body: [Login DTO](#dto-login)

##### Returns
```
{
    "_id": "5aaaad170724cf32akjdi7ea",
    "email": "pudi17@hotmail.com",
    "email_verified": false 
}
```

##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

###### SignUp Exception
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
#### Login
Login types: __Admin__, __User__
* Slug: [_base_url_] /v1/auth/{_type_}/login
* Method: **POST**
* Body: [Login DTO](#dto-login)

##### Returns
```
{
	"access_token": "eyJ0eXAiOiJKV1QiLCJWTNOdyJ9.eyJpc3MiOiJod29yZCJ9.TfZ8zvEBLVv8foqvQXAzlx1yFA0s12K",
	"id_token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI.eyJodHRwczovL2ZpbmFs.4j4gejazpLxosEJc7rmEk_r4uSYECGC",
	"token_type": "Bearer",
	"expires_in": 86400
}
```

##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

###### Login Exception
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

#### Logout
* Slug: [_base_url_] /v1/auth/logout
* Method: **POST**
* Headers: Bearer token
* Body: _NONE_

##### Returns
```
{ }
```

##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

#### Reset password
* Slug: [_base_url_] /v1/auth/reset-password
* Method: **POST**
* Body: [Login DTO](#dto-login)

##### Returns
```
{ }
```

##### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
This model comes from Auth0's plugin. [Documentation](https://github.com/auth0/auth0-java)<br/>

## Room
### Model
### DTO
<h3 id="room-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<h3 id="room-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

## Beacon
### Model
### DTO
<h3 id="beacon-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<h3 id="beacon-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

## Data Items
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

## Data Sources
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

## DataLog
### Model
### DTO
<h3 id="datal-og-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<h3 id="data-log-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

## Data Item Request
### Model
### DTO
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
### API
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>


## Debug
### Model
### DTO
<h3 id="debug-api">API</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>
<h3 id="debug-ws">Web Sockets</h3>
#### Errors
For base errors check here: [Recurring errors](#recurring-errors)<br/>

