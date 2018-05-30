[![Build Status](https://travis-ci.org/FinalworkDigX/SpringbootManager.svg?branch=master)](https://travis-ci.org/FinalworkDigX/SpringbootManager)

# RAM - SpringbootManager

SpringManager is the backend application in the RAM suite.<br/>
Uses Rest API and Web Socket for the [ARMonitoring](https://github.com/FinalworkDigX/ARMonitoringApp) app to get it's data from.

## Setup the project

#### Requirements
1. Working RethinkDB database setup with credentials [Documentation](https://rethinkdb.com/docs/quickstart/)
2. previously setup Auth0 tenant with API and "Machine To Machine connection" [Tutorial](https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/)

#### First launch
First copy example.security.properties to security.properties (located in src >>> main >>> resources). Then complete the missing information.

##### On linux, go to project root and execute these commands.
```
cp src/main/resources/example.security.properties src/main/resources/security.properties
```


#### To get an executable jar file:
```
mvn clean package
```
 
## Using the API &amp; Web Sockets

Full documentation can be found here: [docs.md](/docs.md)