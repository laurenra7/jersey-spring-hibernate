# Jersey 2 / Spring 4 / Hibernate 5 / MySQL 5 example
**Note:** this uses the older
[Hibernate SessionFactory](http://www.baeldung.com/hibernate-4-spring) to
manage database sessions with
[XML Schema-based Spring Beans configuration](https://docs.spring.io/spring/docs/3.0.x/spring-framework-reference/html/xsd-config.html).
For an example using the newer JAVA
[JPA EntityManager](https://www.tutorialspoint.com/jpa/jpa_entity_managers.htm)
and [JavaConfig with annotations](https://docs.spring.io/spring-javaconfig/docs/1.0.0.m3/reference/html/creating-bean-definitions.html)
instead of XML, see the
[JavaConfigAndEntityManager](https://github.com/laurenra7/jersey-spring-hibernate/tree/JavaConfigAndEntityManager)
branch.

This is an example of using these frameworks and libraries to build a
web application backed by a MySQL database.

- Jersey 2
- Spring 4
- Hibernate 5 with MySQL 5 JDBC connector

This project is based on the tutorial at [Jersey 2.x web service +
Integrating with Spring and Hibernate ORM framework using
annotation](http://www.benchresources.net/jersey-2-x-web-service-integrating-with-spring-and-hibernate-orm-framework-using-annotation/)

This has been tested in the following run environment:

- Apache Tomcat 7 servlet container (version 7.0.81) running on Java 8 (1.8.0_144)
- MySQL 5 database (5.7.17)

### Project Requirements

- Java 8 JDK
- Maven 3+ dependency manager

### Get it

Download this project.

```
git clone https://github.com/laurenra7/jersey-spring-hibernate.git
```

### Set it up

Set the JDBC connect string and the username and password for the
database in
[src/main/webapp/WEB-INF/spring-hibernate-jersey2.xml](src/main/webapp/WEB-INF/spring-hibernate-jersey2.xml):

```
    <property name="url" value="jdbc:mysql://db_server:3306/my_database" />
    <property name="username" value="my_user" />
    <property name="password" value="my_password" />
```

### Build it

```
mvn clean package
```

The Java objects for the data model are built automatically each time.
They are located at **generated/java/source/...**

### Deploy it

For example, for a Tomcat 7 servlet container, copy **target/jersey-spring-hibernate.war**
to the Tomcat **webapp** directory where it should automatically unpackage
and deploy.

### Endpoints


GET /rest/check
---------------

Check that web application is running.


GET /rest/books/getall
----------------------

Get all books.

Default response is JSON if no Accept header is specified.


| Headers       |                                    |
| --------------| ---------------------------------- |
| Content-Type: |  application/x-www-form-urlencoded |
| Accept:       | application/json                   |
| Accept:       | application/xml                    |


GET /rest/books/get/{id}
------------------------

Get book with specified ID.

Default response is JSON if no Accept header is specified.


| Headers       |                                    |
| --------------| ---------------------------------- |
| Content-Type: |  application/x-www-form-urlencoded |
| Accept:       | application/json                   |
| Accept:       | application/xml                    |


POST /rest/books/add
--------------------

Add a book using JSON or XML.

Specify which format you are sending in the Content-Type header.

| Headers       |                                    |
| --------------| ---------------------------------- |
| Content-Type: |  application/json                  |
| Content-Type: |  application/xml                   |
| Accept      : |  application/x-www-form-urlencoded |

#### JSON
```json
{
  "bookId": 10,
  "bookName": "Where The Wild Things Are",
  "author": "Maurice Sendak",
  "category": "Childrens"
}
```

#### XML
```
<?xml version="1.0" encoding="UTF-8"?>
<BookType xmlns="http://benchresources.in/cdm/Book">
    <author>Maurice Sendak</author>
    <bookId>10</bookId>
    <bookName>Where The Wild Things Are</bookName>
    <category>Childrens</category>
</BookType>
```

**Note:** xmlns="" must be included exactly as shown in this example.

PUT /rest/books/update
----------------------

Update an existing book using JSON or XML.

The bookId must match an existing book. Find the bookId by querying
with **/rest/books/get/{id}** or **/rest/books/getall**.

Specify which format you are sending in the Content-Type header.

| Headers       |                                    |
| --------------| ---------------------------------- |
| Content-Type: |  application/json                  |
| Content-Type: |  application/xml                   |
| Accept      : |  application/x-www-form-urlencoded |

JSON and XML is the same as the example for **POST /rest/books/add** above.

DELETE /rest/books/delete/{id}
------------------------------

Update an existing book using JSON or XML.

The bookId must match an existing book. Find the bookId by querying
with **/rest/books/get/{id}** or **/rest/books/getall**.

| Headers       |
| --------------|
| --none--      |

