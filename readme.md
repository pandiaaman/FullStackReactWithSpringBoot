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

    application.properties or application.yml :

    Spring boot apps connect to many resources such as
      databases
      message queues
      cloud environments
    we need to know where these resources are located, we can do this in JAVA code, but if these resources change location
    then we need to recompile the java code after updating the location everytime
    So we set the properties in text : application.properties or application.yml
      these contain names of resources in them

=========================================================================================
3.1 Creating components and object(beans) in spring

      REST service object -----> Business logic object -----> data access object

      spring can create the above objects for us called beans
      autowiring : the process of connecting these objects together

      spring works on the logic of inversion of control, where the control of injecting and managing objects
      in spring is held by the IOC container which gives us the lose coupling in spring

      we can tell spring to create an object of a given class by using
        @Component

      @Controller, @RestController, @Service, @Repository all are subsets of @Component

      when we define a class as @Component, we can create beans of that class
      for ex
        Student.java
          @Component("stud")
          class Student{}

        config.xml
          <context:annotation-config />

        Main.java
          class Main{
            ApplicationContext context = new ClassPathXMLApplicationContext("config.xml");
            Student st = context.getBean("stud");
          }

      We can also autowire the object

        class Controller{
          @Autowire
          Service service;

          ...
          service.dosomething();
        }
      ---------------------------
      When we use these annotations, spring automatically create the objects of these classes and store them
      in IOC during application startup, there can be two types of loading :

        Lazy loading
        Fetch loading

      The default scope of spring bean is singleton, that is it will be instantiated only once on the application startup
      by default, spring uses eager loading, that is during startup only, all the beans are loaded.

      Spring boot is actually a factory mechanism for creating beans

      PROCESS:
      when spring boot starts, it does "component scanning" where it looks for all classes in current package which are annotated with @component, once it finds it, it will automatically create an instance of that class during the startup,
      this instance is called a bean, everytime it creates a bean, it provides it an ID, by default it is class name with
      lowercase first letter.
      then, on running the application, we have run method in the main class of project, when run is invoked, spring boot
      will look into the pacakge and all sub packages where the run method class is, for component/service/controller classes.
      if it finds any component classes, it will automatically create instance of these classes, called beans, stored in
      application context, its an object that we get back.
      so application context object contains the bean object, and the applicatonContext object will point to the context object created

3.2 Beans scope and initialization

    -Singleton : default in springboot : single instance created
        our Rest controller, service classes, repositories, we only need one instance of these classes
    -prototype : everytime we ask for a bean, we get a new instance

    to specify : @scope("singleton") and @scope("prototype") can be used

    The problem with singleton beans is that they all get instantiated during the application startup
    if we want the beans to be instantiated only when called(required), we need LAZY initialization

    we can do this by using @Lazy

    scopes for beans available :
    - singleton
    - prototype : give me back a new instance everytime i ask for one
    - request : (web) : a bean devoted for an incoming http request, bean lives only while the request is being processed
    - session : (web) : a bean that lasts while the session lasts
    - application : (web) : bean lives for the lifetime of appliation server, if we restart the server, bean is recreated

3.3 Autowiring

    - if we have depenedency on object of one class then we need to autowire that class's object

          @Controller
          class Controller{
            @Autowired
            Service service;
          }

          @Service
          class Service{
            @Autowired
            Repository repo;
          }

      above, controller needs service class and service class needs Repository class to fulfill its functionalities
      Spring will inject bean of specified type into the field mentioned

      Instead of autwiring the field, we can autowire the constructor, when spring will create the instance of the current
      class it will realise that it needs to import the bean of the other class, so it will get it for us

          class Service{
            Repository repo;

            @Autowired
            public Service(Repository repository){
              this.repo = repository;
            }
          }

    - @Qualifier : what if we have multiple possible beans that can be injected

      Suppose service is an interface and we have multiple implementations of service interface,
      say, StudentService, TeacherService => both implements Service (interface)
      now if we inject Service in the Controller, we need to tell which one to inject using Qualifier

          class Controller{
            @Autowired
            @Qualifier("studentService")
            Service service;

          }

      if we dont do this, we will get runtime error

3.4 Autowiring a collection

    - if we want we can autowire a collection or map of beans as well

        @Component
        class Service{
          @Autowired
          private Collection<Repository> repos; //will contain the pointers to repo objects
        }

3.5 Spring expression language

    - it is used to inject values in the beans
        @Component
        class Student{
          @Value("aman")
          private String name;

          @Value("#{5*10}")
          private int marks;

          @Value("#{ new java.util.Date() }")
          private Date date;
        }

3.6 spring with CLI :: ApplicationArguments

    - ApplicationArguments class help us to receive the arguments from command line
    - we get multiple methods to access the optional and non optional arguments

        getSourceArgs()  : raw args => no format
        getOptionNames() :
        getOptionValues(optionname)
        getNonOptionArgs()


        @Component
        class BeanWithArgs{

          @Autowired
          public BeanWithArgs(ApplicationArguments args){
              //to access args use args.getSourceArgs
          }

        }

=========================================================================================

4.  Configuration classes
    In XML configuration, we define our beans as following :

            <beans xmlns="...">
              <bean class="com.pandiaaman.someclass" name="somename">
                <property name="someproperty">value</property>
                <property name="otherref" ref="somebeanref">
              </bean>
            </beans>

    in this type we have setter injection as well as constructor injection
    above was setter injection
    to use constructor injection we use constructor-arg

          <beans xmlns="..">
            <bean class="com.pandiaaman.somepackage.someclass">
              <constructor-arg index="0" value="v4"/>
              <constructor-arg index="1" value="2"/>
            </bean>
          </beans>

4.1 Defining a configuration class and beans
in java configuration, We use @Configuration and we use @Bean to annotate beans

        @Configuration
        class Conf{

          @Bean
          public Student stud(){
            Student s = new Student();
            s.setName("aman");
            s.setAge(25);

            return s;
          }

        }

        for class Student, we dont need to mention the @Component
        to access from the main app file

        Student st = context.getBean("stud", Student.class);

- Configuration classes are special types of components
  on startup spring boot scans for the components and configuration classes

  To tell spring boot to look for configuration classes in some different pacakges as well

          @SpringBootApplication(scanBasePackages={"mypkgOne","myPkgTwo"})
          public class Application...

  since the main class has @SpringBootApplication and it is configured of below three

  - @Configuration
  - @EnableAutoCongifuration
  - @ComponentScan

  we can also define beans in the main class

  4.2 Managing dependencies in beans

        <bean class="...RefClass" name="refBean">
          <property name="valOne" value="2">
        </bean>

        <bean class="...MainClass" name="mainBean">
          <property name="referenceBean" ref="refBean">
        </bean>

  in java configuration :

        @Configuration
        class confClass{

          @Bean
          public RefClass refBean(){
            RefClass ob = new RefClass();
            ob.setValOne(2);
            return ob;
          }

          @Bean
          public MainClass mainBean(){
            MainClass ob = new MainClass();
            ob.setRefClassVal(refBean());
            return ob;
          }

        }

## =========================================================================================

## +++++++++++++++++++++++++++++++++++++

PROGRESSION ON THE APPLICATION
+++++++++++++++++++++++++++++++++++++++

---

---

=> created root folder and attached git to it
=> created backend spring application from either of the following options

    - spring cli
    - spring initialzr web app
    - eclipse sts

=> applied following dependencies while creating the app

    - spring-starter-web
    - spring-starter-test
    - lombok
    - spring-data-jpa
    - postgres-driver
    - devtools

=> in the application.properties file, add the required lines for connecting to the driver

    - spring.datasource.name
    - spring.datasource.url
    - spring.datasource.password
    - spring.datasource.username
    - spring.jpa.show-sql
    - spring.hibernate.ddl-auto
    - server.port
    - server.servlet.context-path

=> run the application to check if its connecting and running fine

=> create an index.html to test the application url

=> create the first controller class for trial testing the rest controller connection
