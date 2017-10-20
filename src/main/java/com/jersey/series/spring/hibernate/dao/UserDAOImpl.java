package com.jersey.series.spring.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.jersey.series.spring.hibernate.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public String addUser(User user) {

		// insert into database & return Id (primary_key)
		// Hibernate's default strategy for @GeneratedValue on User appears
		// to be GenerationType.IDENTITY so this is inserted and committed
		// to the database in this transaction, not lazily later on.
		entityManager.persist(user);
		return "User saved";
	}

	@Override
	@Transactional
	public User getUser(int userId) {

		User user = (User) entityManager.find(User.class, userId);
		return user;
	}

	@Override
	@Transactional
	public String updateUser(User user) {

		// update database with user information and return success msg
		User updatedUser = entityManager.merge(user);
		return "User information updated successfully";
	}

	@Override
	@Transactional
	public String deleteUser(int userId) {

		// delete user and return success msg
		entityManager.remove((User) entityManager.find(User.class, userId));
		return "User with Id " + userId + " deleted successfully";
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAllUsers() {

		// get all users from database
		List<User> userList = entityManager.createQuery("select a from User a").getResultList();

		return userList;
	}
}