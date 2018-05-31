[![Build Status](https://travis-ci.org/FinalworkDigX/SpringbootManager.svg?branch=master)](https://travis-ci.org/FinalworkDigX/SpringbootManager)

<img src="https://i.imgur.com/hvj4iMi.png" />


# RAM - SpringbootManager

Spring boot backend application that serves as central point to the RAM application suite. Has Rest API and Web Socket end points for the other applications to use.([docs here](/docs.md)). continuously listens to RethinkDB for changes and broadcast those to the previously mentioned Web Socket endpoints.

Other applications in the RAM Application suite:
* [ARMonitoring](https://github.com/FinalworkDigX/ARMonitoringApp): Which is the iOS app that displays all info in Augmented Reality.
* [AngularFrontend](https://github.com/FinalworkDigX/AngularFrontend): serves as the administration panel for this whole suite.

## Setup

### Requirements
1. Working [RethinkDB](https://rethinkdb.com/docs/quickstart/) database setup with credentials.
2. Previously setup Auth0 tenant with API and "Machine To Machine connection" [Tutorial](https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/)

### Build
#### Init security.properties
Copy example.security.properties to security.properties (located in src >>> main >>> resources) and fill in needed information.

##### Linux command
```bash
cp src/main/resources/example.security.properties src/main/resources/security.properties
```
##### Needed information:<br/>
__*RethinkDB:*__
* rethinkdb.username: Username to connect to your deployed RethinkDB instance<br/>
__example:__ admin
* rethink.password: Password to connect to your deployed RethinkDB instance<br/>
__example:__ Admin1
* rethink.host: The URL to where the database is deployed<br/>
__example:__ https://myRethinkDB.com
<br/>

__*Auth0:*__
* auth0.issuer: Auth0 tenant url<br/>
__example:__ https://myTenantsAPI.eu.auth0.com/
* auth0.apiAudience: Auth0 identifier from created API. Best if same as where [SpringbootManager](https://github.com/FinalworkDigX/SpringbootManager) is deployed<br/>
__example:__ https://myApi.com
* auth0.apiConnection: What the user will be suing to login into the application<br/>
__example:__ Username-Password-Authentication
* auth0.managementConnection: What kind of login is needed for the Auth0 client<br/>
__example:__ client_credentials
* auth0.domain: Auth0 API client domain<br/>
__example:__ myTenantsAPI.eu.auth0.com
* auth0.clientId: Auth0 API client id<br/>
__example:__ h6jiLVqBbAj0WZz5QDywJSHD9XCSDqCY
* auth0.clientSecret: Auth0 API client secret<br/>
__example:__ T80x34-6CF4b3OwnU8ZE_pokiHuRvMFqmTNEAsSAmAanZ36mA6lkzsCkjSGS_-Ds


### Generate Jar:
To build to single executable Jar file
```bash
mvn clean package
```

## Using the API &amp; Web Sockets

Full documentation can be found here: [docs.md](/docs.md)

## Example scripts

Multiple scenario's where implemented to showcase RAM. Those are accessible on the URL where This application is deployed.

<img src="https://i.imgur.com/20mQUL0.png" alt="manager test scripts" />