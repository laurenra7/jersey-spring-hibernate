package com.jersey.series.spring.hibernate.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jersey.series.spring.hibernate.model.Book;

@Repository("bookDAO")
public class BookDAOImpl implements BookDAO {

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
	public String insertNewBookInfo(Book book) {

		// inserts into database & return bookId (primary_key)
		int bookId = (Integer) sessionFactory.getCurrentSession().save(book);
		return "Book information saved successfully with Book_ID " + bookId;
	}

	@Override
	@Transactional
	public Book getBookInfo(int bookId) {

		// retrieve book object based on the id supplied in the formal argument
		Book book = (Book) sessionFactory.getCurrentSession().get(Book.class, bookId);
		return book;
	}

	@Override
	@Transactional
	public String updateBookInfo(Book updateBook) {

		// update database with book information and return success msg
		sessionFactory.getCurrentSession().update(updateBook);
		return "Book information updated successfully";
	}

	@Override
	@Transactional
	public String removeBookInfo(Book removeBook) {

		// delete book information and return success msg
		sessionFactory.getCurrentSession().delete(removeBook);
		return "Book information with Book_ID " + removeBook.getBookId() +  " deleted successfully";
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Book> getAllBookInfo() {

		// get all books info from database
		List<Book> lstBook = sessionFactory.getCurrentSession().createCriteria(Book.class).list();
		return lstBook;
	}
}