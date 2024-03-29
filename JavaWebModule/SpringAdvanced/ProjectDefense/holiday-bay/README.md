# Getting Started with Holiday Bay Booking App
Create holiday booking web app to view,add, book rooms in hotels.
It should accept 3 types of users - hotel owners, regular users and admins.
Hotel owners can register hotels into the system. ???
The app should be able to create coupons or vouchers that can be bought by users and can be used upon hotel checkin.
Each user can view his/her own vouchers only. (Vouchers might be downloaded in pdf ...).
Vouchers can be revoked or cancelled by users.
Expired/cancelled vouchers are deleted after certain amount of time from the system: Ex.4 months.
Use separate hotel backend service...
Can use local cache for hotels and rooms ...
Add also a scheduler to change the canEdit flag after .
Add news or blog page.
Add attractions.
Add available bonuses - not given.

Tech stack: frontend - html, js, css, lit-html/angular
backend - java, spring boot, thymeleaf, spring-security etc.
db - MySQL


General Requirements

Your Web application should use the following technologies, frameworks, and development techniques:
•	The application must be implemented using Spring Framework.
o	The application must have at least 12 web pages (views/components).
o	The application must have at least 5 independent entity models.
o	The application must have at least 5 controllers.
o	The application must have at least 5 services.
o	The application must have at least 5 repositories.

•	Use Thymeleaf template engine or make the Front-End using JavaScript,
consuming REST services from a Web API.
•	Use MySQL / Oracle / PostgreSQL / MariaDB as a database.
•	Use Spring Data to access your database.
o	Use Hibernate or any other provider as a JPA implementation.

•	Implement Responsive Web Page Design based on Bootstrap / Google Material Design.
•	Use the standard Spring Security for managing users and roles.
o	Your registered users should have at least these roles: user and administrator.
o	User roles should be manageable from the application.
o	Make sure the role management is secured and error-safe.
o	*Only for retake exam – Users and administrators should be able to edit their usernames.

•	Use Fetch to asynchronously load and display data somewhere in your application.
•	Write tests (Unit & Integration) for your logic, services, repository query methods, helpers, etc.
o	You should have at least 60% coverage on your business logic. / *for retake exam 70% coverage.

•	Implement Error Handling and Data Validation to avoid crashes when invalid data is entered
(both client-side and server-side).
o	When validating data, show appropriate messages to the user

•	Use at least 1 Interceptor. / *for retake exam use at least 2 Interceptors.
•	Schedule jobs that impact the whole application running e.g. once/twice a day.
•	Use ModelМapper or another mapping library.

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web)
* [Validation](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#io.validation)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.security)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#actuator)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring HATEOAS](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.spring-hateoas)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.reactive)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#web.servlet.spring-mvc.template-engines)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.2/reference/htmlsingle/#using.devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

