# Swagger Demo featuring a countryservice
The service serves and saved country definitions from and to a database.
In the local profile this is an H2 in-memory database.
In the default profile the database is postgresql.
The service has a swagger layer to let humans interact with it.

## Features
- Swagger
- Rest api
- The use of *implements Persistable* by the Entity
- FlywayDb
- PostgreSQL
- H2 db
- Using colors for loglevels
- MapStruct (with lombok and implicit type conversion)

## Local endpoints
 - Swagger interface: http://localhost:8080/myportal/countryservice/swagger-ui.html
 - H2 console: http://localhost:8080/myportal/countryservice/h2-console


## Configuration
 - Local profile VM Option: -Dspring.profiles.active=local


## Environment Variables

| Name                 | Default value | Description |
|----------------------|---------------| ---- |
| LOGLEVEL_SPRING      | INFO          | The loglevel for the `org.springframework*` packages
| LOGLEVEL_APPLICATION | INFO          | The loglevel for the `org.boip.util.countryservice*` packages
| LOGLEVEL_HIBERNATE   | ERROR         | The loglevel for the `org.hibernate*` packages
| DB_CONNECT_URL       |               | The connect string for the database connection
| DB_USERNAME          |               | The user to connect to the DB
| DB_PASSWORD          |               | The password for the db user.
| SHOW_SQL             | false         | When true,  the SQL statements are  shown in the log

Test Change


