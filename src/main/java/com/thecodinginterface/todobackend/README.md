# Demo Spring Boot / Spring MVC REST API Backend for a Common Todos App

### Running App

Database is meant to be PostgreSQL and configs are in application.properties file as shown below.

```
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_NAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.database-platform=org.hibernate.dialect.PostgresPlusDialect
spring.jpa.hibernate.ddl-auto=none
spring.datasource.initialization-mode=always
spring.jpa.hibernate.show-sql=true
```

Example of environment variables for IntelliJ Project

Database schema is defined in src/main/resources/schema.sql and is built / ran automatically as long as an application is provided in the database URI (springtodos in example above) thanks to spring boot jpa.

There are two default users that the app seeds and can be used for basic auth

- Username "adam" / Password "adam" who has READ and WRITE authorizations
- Username "marley" / "marley" who has READ authorizations


### Experiment with Using [Httpie](https://httpie.org/)

Create a new Todo

```
$ http --auth adam:adam POST http://localhost:8080/api/v1/todos \
  title="put kids to bed" \
  description="kids are cranky, sleep is needed" \
  status="Incomplete" \
  user:='{"id":1}'
HTTP/1.1 201 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Connection: keep-alive
Content-Type: application/json
Date: Fri, 10 Jul 2020 03:03:56 GMT
Expires: 0
Keep-Alive: timeout=60
Pragma: no-cache
Set-Cookie: JSESSIONID=4BF89B8E3E6778BA56DAAC84C25809CF; Path=/; HttpOnly
Transfer-Encoding: chunked
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

{
    "description": "kids are cranky, sleep is needed",
    "id": 1,
    "status": "Incomplete",
    "title": "put kids to bed"
}
```

Be denied creating a todo

```
$ http --auth marley:marley POST http://localhost:8080/api/v1/todos \
  title="put kids to bed" \
  description="kids are cranky, sleep is needed" \
  status="Incomplete" \
  user:='{"id":1}'
HTTP/1.1 403 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Connection: keep-alive
Content-Type: application/json
Date: Fri, 10 Jul 2020 03:12:18 GMT
Expires: 0
Keep-Alive: timeout=60
Pragma: no-cache
Set-Cookie: JSESSIONID=197A5613EEEFFFCC81A3143D2F46D761; Path=/; HttpOnly
Transfer-Encoding: chunked
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

{
    "error": "Forbidden",
    "message": "",
    "path": "/api/v1/todos",
    "status": 403,
    "timestamp": "2020-07-10T03:12:18.029+00:00"
}
```

Update Todo

```
$ http --auth adam:adam PUT http://localhost:8080/api/v1/todos/1 \
  title="put kids to bed" \
  description="kids are cranky, sleep is needed" \
  status="Completed" \
  user:='{"id":1}'
HTTP/1.1 200 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Connection: keep-alive
Content-Type: application/json
Date: Fri, 10 Jul 2020 03:16:16 GMT
Expires: 0
Keep-Alive: timeout=60
Pragma: no-cache
Set-Cookie: JSESSIONID=526F8E76552F792E7EA8EDFEEA05CE4A; Path=/; HttpOnly
Transfer-Encoding: chunked
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

{
    "description": "kids are cranky, sleep is needed",
    "id": 4,
    "status": "Completed",
    "title": "put kids to bed"
}
```

List Todos

```
$ http --auth adam:adam http://localhost:8080/api/v1/todos       
HTTP/1.1 200 
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Connection: keep-alive
Content-Type: application/json
Date: Fri, 10 Jul 2020 03:21:42 GMT
Expires: 0
Keep-Alive: timeout=60
Pragma: no-cache
Set-Cookie: JSESSIONID=604ADA3316A43F9765335F3FED3FE247; Path=/; HttpOnly
Transfer-Encoding: chunked
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block

[
    {
        "description": "kids are cranky, sleep is needed",
        "id": 1,
        "status": "Completed",
        "title": "put kids to bed"
    },
    {
        "description": "Help people get up and running with demo app",
        "id": 2,
        "status": "Incomplete",
        "title": "Write Spring Todo Backend README"
    },
    {
        "description": "It is always nice to have docs for your API :)",
        "id": 3,
        "status": "Incomplete",
        "title": "Maybe document with Swagger someday"
    }
    
]
```
