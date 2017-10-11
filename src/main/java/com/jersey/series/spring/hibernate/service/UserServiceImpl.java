package com.jersey.series.spring.hibernate.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.series.spring.hibernate.dao.UserDAO;
import com.jersey.series.spring.hibernate.db.resources.UserListType;
import com.jersey.series.spring.hibernate.db.resources.UserType;
import com.jersey.series.spring.hibernate.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import in.benchresources.cdm.book.BookListType;
//import in.benchresources.cdm.book.BookType;

@Component
@Path("/users")
public class UserServiceImpl implements IUserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO userDAO;

	// Basic CRUD operations for Book Service

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/addbook
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveUser(UserType userType) {

		log.info("doing createOrSaveUser...");

		// unmarshall userType and set in Model object User
		User user = new User();
		user.setUserId(userType.getUserId());
		user.setUserName(userType.getUserName());
		user.setEmailAddress(userType.getEmailAddress());
		return userDAO.addUser(user);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getbook/1
	@GET
	@Path("get/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserType getUser(@PathParam("id") int userId) {

		log.info("GET user " + userId);

		// get user and marshall into JSON or XML and return it
		User user = userDAO.getUser(userId);

		UserType userType = new UserType();
		userType.setUserId(user.getUserId());
		userType.setUserName(user.getUserName());
		userType.setEmailAddress(user.getEmailAddress());
		return userType;
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/updatebook
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateUser(UserType userType) {

		// unwrap userType and set in Model object User
		User user = new User();
		user.setUserId(userType.getUserId());
		user.setUserName(userType.getUserName());
		user.setEmailAddress(userType.getEmailAddress());

		// update user & return SUCCESS message
		return userDAO.updateUser(user);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/deletebook/5
	@DELETE
	@Path("delete/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteUser(@PathParam("id") int userId) {

		// delete book info & return SUCCESS message
		User user = new User();
		user.setUserId(userId);
		return userDAO.deleteUser(user);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getallbook
	@GET
	@Path("getall")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserListType getAllUsers() {

		log.info("getall users...");

		List<User> userList = userDAO.getAllUsers();

		// create a object of type BookListType which takes book objects in its list
		UserListType userListType = new UserListType();

		for(User user : userList){
			UserType userType = new UserType();
			userType.setUserId(user.getUserId());
			userType.setUserName(user.getUserName());
			userType.setEmailAddress(user.getEmailAddress());
			userListType.getUserType().add(userType); // add to userListType
		}
		return userListType;
	}
}