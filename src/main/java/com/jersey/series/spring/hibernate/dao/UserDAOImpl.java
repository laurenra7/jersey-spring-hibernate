package com.jersey.series.spring.hibernate.dao;

import java.util.List;

import com.jersey.series.spring.hibernate.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public String addUser(User user) {

		// insert into database & return Id (primary_key)
		int userId = (Integer) sessionFactory.getCurrentSession().save(user);
		return "User saved successfully with ID " + userId;
	}

	@Override
	@Transactional
	public User getUser(int userId) {

		User user = (User) sessionFactory.getCurrentSession().get(User.class, userId);
		return user;
	}

	@Override
	@Transactional
	public String updateUser(User user) {

		// update database with user information and return success msg
		sessionFactory.getCurrentSession().update(user);
		return "User information updated successfully";
	}

	@Override
	@Transactional
	public String deleteUser(User user) {

		// delete user and return success msg
		sessionFactory.getCurrentSession().delete(user);
		return "User with Id " + user.getUserId() +  " deleted successfully";
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAllUsers() {

		// get all books info from database
		List<User> userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();
		return userList;
	}
}