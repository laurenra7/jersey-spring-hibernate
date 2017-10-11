package com.jersey.series.spring.hibernate.service;

import com.jersey.series.spring.hibernate.db.resources.UserListType;
import com.jersey.series.spring.hibernate.db.resources.UserType;

public interface IUserService {

	// Basic CRUD operations for Book Service

	public String createOrSaveUser(UserType user);
	public UserType getUser(int userId);
	public String updateUser(UserType user);
	public String deleteUser(int userId);
	public UserListType getAllUsers();
}