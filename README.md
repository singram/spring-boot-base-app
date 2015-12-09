# spring-boot-base-app
Basic spring boot application with standard libraries for experimentation

## Eclipse notes
Necessary changes to exclude code from the automatic formatter can be found here
Note. Eclipse formatting in Mars appears to be broken (http://stackoverflow.com/questions/31048040/how-to-force-the-eclipse-mars-4-5-formatter-not-to-join-already-wrapped-lines)
 http://www.eclipseonetips.com/2013/10/15/disable-eclipse-formatting-for-certain-sections-of-code-only/

## Refs
### spring-data-rest
http://docs.spring.io/spring-data/rest/docs/current/reference/html/

## Validation notes
http://blog.codeleak.pl/2014/06/better-error-messages-with-bean.html
http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html
http://stackoverflow.com/questions/24318405/spring-data-rest-validator
http://www.bernhardwenzel.com/blog/2014/10/10/spring-boot-rest-knockout-bookmarks#the-domain-model

## Testing
http://www.jayway.com/2013/12/10/json-schema-validation-with-rest-assured/
http://www.jayway.com/2014/01/14/unit-testing-spring-mvc-controllers-with-rest-assured/
https://github.com/flyway/flyway-test-extensions/wiki/Usage-flyway-spring-test
https://github.com/Hippoom/spring-test-dbunit-template

http://g00glen00b.be/spring-boot-rest-assured/
http://www.javabeat.net/spring-boot-testing/
http://blog.czeczotka.com/2015/01/20/spring-mvc-integration-test-with-rest-assured-and-mockmvc/
http://www.joecolantonio.com/2014/02/26/rest-testing-with-java-part-two-getting-started-with-rest-assured/

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

## Credits/ Contributions

Bill Koch (https://github.com/billkoch)
- Integration test improvements and fixes.


## To Investigate

- Auto reload
- Lombock
- Expanded manifest/info
- Profiles
- Logging
- Statsd compatibility
- Actuator
- Multi db support
- Redis caching