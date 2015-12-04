# spring-boot-base-app
Basic spring boot application with standard libraries for experimentation

## Refs
### spring-data-rest
http://docs.spring.io/spring-data/rest/docs/current/reference/html/

## Validation notes
http://blog.codeleak.pl/2014/06/better-error-messages-with-bean.html
http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html
http://stackoverflow.com/questions/24318405/spring-data-rest-validator
http://www.bernhardwenzel.com/blog/2014/10/10/spring-boot-rest-knockout-bookmarks#the-domain-model

## Access notes
alps+json
hal+json
schema+json
### JSON Schema
curl -H 'Accept:application/schema+json' http://localhost:8080/profile/persons
### ALPS
curl http://localhost:8080/profile/persons
### Post new record
curl -i -X POST -H "Content-Type:application/json" -d '{  "firstName" : "Frodo",  "lastName" : "Baggins" }' http://localhost:8080/people


## To Investigate

- Auto reload
- Html cleanup
- CRUD People
- Lombock
- Expanded manifest/info
- Profiles
- Logging
- Statsd compatibility
- Actuator
- Multi db support
- Redis caching