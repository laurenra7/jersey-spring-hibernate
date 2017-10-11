package com.jersey.series.spring.hibernate.dao;

import java.util.List;

import com.jersey.series.spring.hibernate.model.User;

public interface UserDAO {

	public String addUser(User user);
	public User getUser(int userId);
	public String updateUser(User user);
	public String deleteUser(User user);
	public List<User> getAllUsers();
}