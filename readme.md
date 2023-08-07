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
