This is an Oreilly project with Spring boot backend and react frontend.
Working

1. Setting the scene
2. creating and running spring boot application
3. spring boot components and beans
4. configuration classes
5. spring boot techniques
6. integration with data sources
7. working with spring data repositories
8. implemnting and consuming REST services
9. creating a react frontend
10. consuming a REST api from react
11. implemnting a compelling UI in react

=======================================================================

BEST PRACTICES UTILIZED

- write the correct data structure for the given problem
- write compact code (use of ternary, streams etc)
- avoide creating unnecessary variables in code (saves space)
- avoid abbreviations : use fully explained names for functions and variables
- in larger code base, the comments can become stale, that means, if we write lots of comments for every line of code
  a new dev in team might change the code but not update the comments, hence the comments can become stale, so keep them concise
  and make sure to use fully explained names for functions and variables
- keep it simple silly, dont try to be too clever and insert things into each other until they become mess
  (make sure the code is readable)
- too much nested code kills code readability : dont make cases and if elses too nested, try to see if there is better concise way
- consistent coding style and naming convention : either only use camel case or snake or one of your choice, but dont change
- there should be NO code duplication, duplicate code should be entered in a function and that function should be called where needed

JAR vs WAR vs EAR

    JAR : java archive file : file with java classes, associated metadata and resources like text, images etc combined
      into a single file to execute application
      can contain a built in server and hence can be run as a standalone java app
    WAR : web archive file : contains files such as servlets, html, jsp and js etc.,necessary to develop web app
    EAR : enterprise archive file : java EE file that packs one or more modules into a single archive to deploy them on app server
      allows testing and devloping web apps easily

=======================================================================
1.1 Overview of full stack application

    WEB UI <=REST service=> WEB SERVER <=> DB Persistence

    web ui : html, css, sass, tailwind, javascript, typescript, framework (REACT)
    REST services : works on HTTP connection : most common way for web ui to communicate with a server
      (any kind of client can communicate with any kind of server)
    Web server app : spring boot, MS ASP.NET, Python, Node.js etc (tech used here is invisible to the client : abstraction)
    persistence/db : Relational(MYSQL, PostgreSQL), NOSQL (mongoDB, Cassandra), cloud storage(AWS s3), mainframe (IBM),

1.2 Understanding REST services

    serves as the communication tool between the web ui and the server
    Representational state transfer
    the idea is to have similar functioning for data apps as to the web apps
    like in the web apps, each unique page has its own unique url => in data app(rest) each data endpoint has its own unique url
    a client sends an HTTP request message to the REST service and the REST service returns an HTTP response message
    HTTP request :
      -Header :
        POST /employees HTTP/1.1 => HTTP verb (GET,PUT,POST,DELETE,PATCH) | HTTP request version
        Host : someExample.com => the host url we are trying to access for the request
        content-type : application/json => type of the body content
        accept : application/json =>what format we accept the returned data
        content-length : 27 => length of the message

      -{name : "aman", age : 25}

    HTTP verbs : (CRUD operations)
      1. GET : (read) : read a resource
      2. POST : (create) : create a new resource from the request data
      3. PUT : (update) : updates a resource from the request data
      4. PATCH : (update)
      5. DELETE : (delete) : deletes a resource

    REST mainly communicates in JSON (java script standard object notation) : it contains the key value pair

    we can change the format of data communicated to XML then we can simply put accept as application/xml and while recieving we
    can also use @PostMapping(produces="application/xml", consumes="application/xml", value="/employees")

    HTTP Response :
      -Header :
        HTTP/1.1 success 201 => status code of response, can be success(200), redirect(300), client error(400), server error(500)
        Location 4 => the location where the new item has been inserted(this we can mention what to send in ResponseEntity)
        content-type => application/json
        content-length => 37
        Date : 8 Aug 2023 18:34:11 IST

        {id: 4, name : "aman", age:25} => body

    Http status codes :
      200 : OK
      201 : Created
      204 : no content
      301 : permanently moved (redirection)
      400 : bad request
      401 : unauthorized / (unauthonticated)
      403 : forbidden (not a valid user)
      404 : page not found (resource not exists)
      405 : mehtod not allowed (if we go for PUT where only POST is expected)
      500 : internal server error (backend side issue)

1.3 Creating client side content

    HTML, CSS, JS(ES6/2015, dynamically typed), TS(descriptive, secure, statically typed)
    framework : React (lightweight)

==================================================================================

2.1 Creating a simple application using spring boot

    app folder : contains pom.xml that has the dependencies needed for the app to run
    src > main : java code and java resources
    src > test : java test code

2.2 Implementing a REST service

    - client only communicates using the REST service, rest provides abstraction and hides important info from the server
    - use @RestController and @RequestMapping with the controller
