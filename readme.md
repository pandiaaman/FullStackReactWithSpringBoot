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

5. Spring Boot techniques
   ( dev :filters, interceptors, AOP, actuators, profiles, state management, advisors, error handlers, controllers, services, dto, repositories
   API : GraphQL, Rest
   security : oauth2 odic jwt okta,
   testing : unit, regression, integration, mockito, testcontainers, selenium,
   message queues : kafka
   deploy : git actions, ci cd, jenkins, docker, kubernetes, aws)

5.1 setting application properties in spring boot using COMMAND LINE
application.properties

        name = "aman pandia"

to use this property in bean class

        @Component
        class MyBean{

          @Value("${name}")
          private String name;
        }

- when the run method from the Main Application class is invoked, it looks for the application.properties / yml file
  it looks in the root of the classpath by default, root of the classpath in maven is resources folder

=> Spring boot lets us define application properties in multiple places such as
(from higher to lower priority)

- command line arguments
- operating system environment variables
- application properties outside jar
- application properties inside jar (application.properties/application.yml)

ex. if we are having the application as jar file in a docker, then we might need to add few application properties
that we need to override the ones already present in application.properties, so the properties outside the jar overrides them

5.2 specifying which properties file to use

- where all can we store the application.properties
  -> config folder in main project app > inside main project app > in config folder in resorces > inside resources

- Since we have so many options to put our properties file, how to tell spring which one to use
  say we have a configFile.proeprties, we can do the below import for System.setProperty

        @SpringBootApplication
        public class Application{
          public static void main(String[] args){

            System.setProperty("spring.config.name","configFile")
            ApplicationContext context = SpringApplication.run(Application.class, args);
          }
        }

- we can also set the above file name as a command line argument as follows :

              --spring.config.name=configFile

to do this, we need to create a new run configuration, inside program arguments

5.3 Defining YAML properties file:

          server:
            port: 8081
            servlet:
              context-path: /appname
          spring:
            datasource:
              url: ...
              username:root
              name:testdb
              password:pass
            jpa:
              show-sql: true
              hibernate:
                ddl2auto: update

5.4 Using spring profiles
allows us to run the application in different modes : we can distinguish properties based on modes
we can use some properties (connection strings) in the development mode and some in the production mode

we can have "development", "test" and "production" profiles

=> 1. we can have profile specific Components

    @Component
    @Profile("development")
    class ServiceImplDev implements Service{

      @Override
      public String toString(){
        return "service in Dev";
      }
    }
    --------------
    @Component
    @Profile("production")
    class ServiceImplProd implements Service{

      @Override
      public String toString(){
        return "service in Prod";
      }
    }

=> 2. we can define profile specific properties
(YAML file) : profile separator : ---

    apiserver:
      address: 192.168.0.100
      port: 8080
    ---
    spring:
      config:
        activate:
          on-profile: development  <==
    api-server:
      address: 127.0.0.1
    ---
    spring:
      config:
        activate:
          on-profile: production  <==
    api-server:
      address: 192.168.2.120
      port:8083

=> How to tell which profile to use (in properties file)

    spring.profiles.active = development

5.5 Spring boot actuator
includes features to monitor health metrics of the application

it can provide info as:

- http endpoints
- JMX : java management extension
- remote shell : ssh or telnet

        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

  to enable all actuator endpoints :

        management.endpoints.web.exposure.include=*

        ------
        in yaml

        management:
          endpoint:
            health:
              show-details: always
          endpoints:
            web:
              exposure:
                include:
                - metrics
                - info
                - health

  if we dont set the above property in properties file, then by default we get access to these two endpoints

        /actuator/health
        /actuator/info

  to check the info provided by actuator, after adding the dependency go to

        http://localhost:8081/appname/actuator/mappings
        http://localhost:8081/oreillydevapp/actuator/health
        http://localhost:8081/oreillydevapp/actuator/metrics
        http://localhost:8081/oreillydevapp/actuator/info

6 Spring Data JPA

6.1 understanding Spring data

- spring boot provides excellent support to connect with multiple types of databases, be it relational, or non relational
- spring provides us the declarative transaction management, which means that we do not exclusively need to commit the
  transactions, or rollback them in case of errors, spring does this automatically, we only need to annotate the method
  with @Transactional
- spring automatic connection management : acquires/releases connections automatically
- spring provides us with CrudRepository and JPARepository, that provide us with object mapping abstractions
  spring also gives us the dynamic query creation :: findByName, findByRegion.

  6.2 JPA (Java persistence API) :: standard ORM (object relational mapping) API
  => implemented by Hibernate library (goes well with spring)

        entity class => java class with its fields mapped to the table
        entity manager class in JPA => fetches/saves entities to the database
        entity manager factory =>creates and configures an entity manager so it can connect to db

        dependency required :
          spring-boot-starter-data-jpa

        (adds hibernate as well)

  ***

        <configuration>
          <properties>
            <...>
          </properties>
          <session-factory>
            <property name="connection.url"></property>
            <property name="connection.username"></property>
            <property name="connection.password"></property>
            <property name="show-sql">true</property>
            <property name="hbm2ddl.auto">update</property>
          </session-factory>
        </configuration>

  ***

        Configuration cfg = new Configuration("hibconf.cfg.xml");
        SessionFactory factory = cfg.buildSessionFacotry();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        String str = "from Student order by marks";
        Query query = session.createQuery(str);
        List result = query.list();

        tx.close();

using JDBC with normal java

    class.forName("mysql..cj..");
    Connection con = DriverManager.getConnection(url, username, password);
    String query = "select * from ... where valOne=?, valTwo=?";
    PreparedStatement pst = con.prepareStatement(query);
    pst.setString(1,"adsf");
    pst.setInt(2,23);
    ResultSet rs = pst.executeQuery();
    while(rs.hasNext()){
      ..rs.getString("");
    }

in spring, we have JDBC template, that can be used with DataSource object

    @Configuration
    class Conf{
      @Bean
      DataSource ds(){
        DataSource ds = new DataSource();
        ds.setUrl();
        ds.setUsername();
        ds.setPassword();

        return ds;
      }

      @Bean
      JDBCTemplate temp(){
        JDBCTemplate temp = new JDBCTemplate();
        temp.setDataSource(ds());

      }
    }

==> to enable SQL logging for debugging process

    #Turn Statistics on and log SQL stmts

    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true

    #If want to see very extensive logging
    spring.jpa.properties.hibernate.generate_statistics=true
    logging.level.org.hibernate.type=trace
    logging.level.org.hibernate.stat=debug

6.3 Defining JPA entity classes

    @Entity
    @Table(name="user_table")
    class User{
      @Id
      @GeneratedValue(strategy= GenerationType.UUID)
      private String userId;
      @Column(name="user_name")
      private String userName;
      private int userAge;
      private String userEmail;

    }

=> using H2 database with our projects :
H2 is an in memory database which is completely empty when the application starts, it is alive while the app is up and
removes all the data once we stop the application

we need to seed data in the H2 database when the application starts so that we have some data to play around

to see the h2 data in console and interact with it:

    h2:
    console:
      enabled: true
      path: /h2-console

h2 console :

    http://localhost:8081/oreillydevapp/h2-console

when we have to insert, update and delete data in JPA, we need to use @Transactional

7. Working with Spring data repositories

methods we can use with CrudRepository

        public interface CrudRepository<T,ID>{

          long count();
          void delete(T entity);
          void deleteAll();
          void deleteById(ID id);
          boolean existsById(ID id);
          iterable<T> findAll();
          Iterable<T> findAllById(Iterable<ID> ids);
          T save();
          Iterable<T> saveAll(Iterable<T> entities);
        }

PagingAndSortingRepository extends CrudRepository

- JPARepository inherits from the PagingAndSortingRepository
- MongoRepository inherits from the PagingAndSortingRepository too

  //below we have some custom query methods in repository

        List<TrialEmployee> findByEmployeeRegion(String region);

        //we have JPQL below
        @Query("select e from TrialEmployee e where e.employeeSalary >= ?1 and e.employeeSalary <= ?2")
        List<TrialEmployee> findInSalaryRange(double from, double to);

        Page<TrialEmployee> findByEmployeeSalaryGreaterThan(double salary, Pageable pageable);

=> if we want the main application to look for repository in some other folder then we need

      @SpringBootApplication
      @EnableJPARepositories({"packageOne","packageTwo"})
      public class Application{

      }

==> MongoDB (NOSQL database)

there are several types of noSQL Db

- MongoDB(Document based database) :: equivalent of row is a document, document needs not to have the same schema
  (table is a collection in mongodb)
- Redis (Key value store database : like a dictionary)
- Cassandra (column oriented database) : data analytics become faster
- Neo4j : Graph database

MONGODB :

- in mongodb the data is stored in bson

        //to insert data in mongodb

        db.collectionname.insertOne(
          {name:"aman"",gender:"M",age:25}
        )

        //to insert many documents at once

        db.collectionname.insertMany(
          [
            {name:"aman",gender:"M",age:25},
            {name:"anisha",gender:"F",age:25,marks:20},
            {name:"ayaz",gender:"M",age:25,favTeam:"India"}
          ]
        )

        //to find documents in collection

        db.collectionname.find()

        //find document based on criteria => where clause

        db.collectionname.find(
          {name:"aman"}
        )

        //notations : : $or, $and, $gt, $lt

        db.collectionname.find(
          {
            $or:[
              {age : {$lt: 20} },
              {salary: {$gt: 5000}}
            ]
          }
        )

        db.collectionname.find({
          $and:[
            {salary : {$lt : 3000}},
            {salary : {$gt : 1000}}
          ]
        })

=>mongodb by default uses 27017

8. Rest Services

8.1 WebFlux vs web
=> we have two types of dependencies, web and webFlux(reactive), where web offers us a single request response pattern of rest services, webflux can be used if we want to send long streams of data back to the client after one single call

=> Rest service is an endpoint in the application, to which the client can connect to
(the methdos in rest service are mapped to a URL)
data sent and received is generally JSON and can also be XML

8.2 How the rest services work in spring MVC

- We have dispatcher servlet (bean created by spring boot) -> this is the front controller, where each incoming request is first received, it then checks which controller is the request targetted to and moves the request there

- Spring boot automatically converts the objects to JSON using the JSON serializer bean

        @Controller/@RestController
        @RequestMapping
        @CrossOrigin
        ---------
        @GetMapping(value="/employees", produces={"application/json","application/xml"})
        @PostMapping
        @PutMapping
        @DeleteMapping

- We return data using ResponseEntity

        @RestController
        @RequestMapping(value="/api")
        @CrossOrigin("http://localhost:3000")
        class ControllerClass{

          @Autowired
          private ProductService service;

          @GetMapping(value="/products/{id}, produces={"application/json","application/xml"})
          public ResponseEntity<Product> getSingleProduct(@PathVariable("id") String id){
            try{
              Product p = service.getProductById(id);
              return ResponseEntity.status(HttpStatus.OK).body(p);
            }catch(Exeption e){
              e.printStackTract();
            }
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
          }
        }

=> passing parameters to the REST urls

1.  first way is to send it as a path variable : http://localhost:8081/products/3
2.  second way is to send it as an optional parameter in the url : http://localhost:8081/products?min=100

        //for path variable

        @GetMapping(value="/products/{id}")
        public ResponseEntity<Product> getProductById(@PathVariable("id") String id){
          ...
        }

        //for optional parameter in url

        @GetMapping(value="/products")
        public ResponseEntity<Product> getProductMoreThanPrice(@RequestParam(value="min", required=false, defaultValue="0.0") double min){
          List<Product> productsLessThanMin = allProducts.stream().filter(p -> p.getPrice() > min).collect(Collectors.toList());
        }

=> POST request

      @RestController
      @RequestMapping("/api")
      @CrossOrigin
      class MainController{

        @PostMappin(value = "/products",
          produces = {"application/json","application/xml"},
          consumes = {"application/json","application/xml"})
        public ResponseEntity<Product> addProduct(@RequestBody Product product){
          service.addProduct(product);
          return ResponseEntity.status(HttpStatus.CREATED).body(product);
        }
      }

=> PUT request : gets both the requestId and body

8.3 Defining a REST client in spring :: RestTemplate

we can create a separate project as a rest client, this is what happens in microservices, when we are communicating among multiple services
To do this, we create a component that makes calls to a rest server, using a restTemplate class

=> while sending and receiving, the objects are serialized and deserialized automatically by the restTemplate

client has a separate spring boot application running on a different port than the server

The Product class (the class being transferred) will be present in both the applications

methods in RestTemplate

      - GetForEntity

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
