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

## Web Socket basics
Web sockets currently need no credentials to be used. This may changed as the backend evolves.

### Summary of models using Web Sockets
1. [Beacon](#beacon-ws)
2. [Room](#room-ws)
3. [DataLog](#datalog-ws)
4. [Debug](#debug-ws)

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
parameter   |required  
------------|:--------:
client_id   |Yes
connection  |No 
password    |Yes 

@JsonProperty("client_id")
    private String clientId;
    @JsonProperty("connection")
    private String connection;
    @JsonProperty("password")
    private String password;
    @JsonProperty("verify_password")
    private Boolean verifyPassword;
    @JsonProperty("username")
    private String username;
    @JsonProperty("email")
    private String email;
    @JsonProperty("email_verified")
    private Boolean emailVerified;
    @JsonProperty("verify_email")
    private Boolean verifyEmail;
    @JsonProperty("phone_number")
    private String phoneNumber;
    @JsonProperty("phone_verified")
    private Boolean phoneVerified;
    @JsonProperty("verify_phone_number")
    private Boolean verifyPhoneNumber;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("picture")
    private String picture;
    @JsonProperty("name")
    private String name;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    )
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    )
    @JsonProperty("updated_at")
    private Date updatedAt;
    @JsonProperty("identities")
    private List<Identity> identities;
    @JsonProperty("app_metadata")
    private Map<String, Object> appMetadata;
    @JsonProperty("user_metadata")
    private Map<String, Object> userMetadata;
    @JsonProperty("multifactor")
    private List<String> multifactor;
    @JsonProperty("last_ip")
    private String lastIp;
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    )
    @JsonProperty("last_login")
    private Date lastLogin;
    @JsonProperty("logins_count")
    private Integer loginsCount;
    @JsonProperty("blocked")
    private Boolean blocked;
    private Map<String, Object> values;
### DTO
<h3 id="auth0-management-api">API</h3>
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

