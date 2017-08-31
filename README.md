# facebookLocations

Small project made for an interview. The purpose of it was creating REST service, that will provide a method, that will return name
of place and its coordinates (latitude and longitude) as JSON. Search is made using Facebook Graph API and is filtered by country 
and city, both passed along with searched place name as request parameters.

Technologies used during development:
* Facebook4J
* Gson
* Spring Boot
* JUnit
* Mockito
* AssertJ
* Lombok
* Java 8

Prerequisites:
* Java 8 JDK installed, environment variable are set up: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* properties in application.properties are corectly set according to Facebook Graph API credentials:

```
token=<REPLACE_WITH_TOKEN>
app_id=<REPLACE_WITH_APP_ID>
app_secret=<REPLACE_WITH_APP_SECRET>
```

## Usage

To start application using maven plugin call command as presented below:

```
mvn spring-boot:run
```

Application should start on port 8080:

```
2017-08-31 20:27:47.661  INFO 16288 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-08-31 20:27:47.730  INFO 16288 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8080 (http)
2017-08-31 20:27:47.740  INFO 16288 --- [           main] com.fb.locations.LocationsApplication    : Started LocationsApplication in 2.809 seconds (JVM running for 7.62)
```

To get name and coordinates call REST service, ie. by curl command from terminal:

```
curl -X GET 'http://localhost:8080?name=Egnyte&country=Poland&city=Poznan'
```

Result should look like below:

```
[{"name":"Egnyte Poland","latitude":52.40474913,"longitude":16.940680915}]
```
