# Jersey 2 / Spring 4 / Hibernate 5 / MySQL 5 example
This is an example of using these frameworks and libraries to build a
web application backed by a MySQL database.

- Jersey 2
- Spring 4
- Hibernate 5 with MySQL 5 JDBC connector

Change the username and password properties in spring-hibernate-jersey2.xml
to match your MySQL user and password.

This project is based on the tutorial at [Jersey 2.x web service +
Integrating with Spring and Hibernate ORM framework using
annotation](http://www.benchresources.net/jersey-2-x-web-service-integrating-with-spring-and-hibernate-orm-framework-using-annotation/)

### Project Requirements

- Java 8 JDK
- Maven 3+ dependency manager

### Get it

Download this project.

```
git clone https://github.com/laurenra7/jersey-spring-hibernate.git
```

### Set it up

Set the JDBC connect string and the username and password properties in
**src/main/webapp/WEB-INF/spring-hibernate-jersey2.xml**

```
    <property name="url" value="jdbc:mysql://db_server:3306/my_database" />
    <property name="username" value="my_user" />
    <property name="password" value="my_password" />
```

### Build it

```
mvn clean package
```

### Deploy it

For example, for a Tomcat 7 servlet container, copy **target/jersey-spring-hibernate.war**
to the Tomcat **webapp** directory where it should automatically unpackage
and deploy.

### Endpoints

| HTTP Method | Description                           | URL                                                    |
| ----------- | ------------------------------------- | ------------------------------------------------------ |
| GET         | Check that web application is running | (host url)/jersey-spring-hibernate/                    |
| GET         | Get all books                         | /jersey-spring-hibernate/rest/bookservice/getallbook   |
| GET         | Get a book (id = 1)                   | /jersey-spring-hibernate/rest/bookservice/getbook/1    |
| POST        | Add book (JSON or XML)                | /jersey-spring-hibernate/rest/bookservice/addbook      |
| PUT         | Update book (JSON or XML)             | /jersey-spring-hibernate/rest/bookservice/updatebook   |
| DELETE      | Delete a book (id = 3)                | /jersey-spring-hibernate/rest/bookservice/deletebook/3 |

### Book JSON and XML

```
{
  "bookId": 10,
  "bookName": "Where The Wild Things Are",
  "author": "Maurice Sendak",
  "category": "Childrens"
}
```

### Build POJOs from XML schema
