package com.jersey.series.spring.hibernate.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jersey.series.spring.hibernate.model.Book;

@Repository("bookDAO")
public class BookDAOImpl implements BookDAO {

	private static final Logger log = LoggerFactory.getLogger(BookDAOImpl.class);

	@PersistenceContext(unitName = "LOCAL_PERSISTENCE")
	private EntityManager entityManager;

	@Override
	@Transactional
	public String insertNewBookInfo(Book book) {

		log.info("attempting to persist book " + book.toString());

		// Because Book has a GenerationType.IDENTITY, this inserts into
		// database and commits right away. Other types would result in lazy
		// commit (which could be forced with .flush() or .commit()).

		entityManager.persist(book);
//		entityManager.flush();
		log.info("successfully persisted book. Was it committed to the database? Trying to get ID...");
		int bookId = book.getBookId();
		log.info("book id = " + bookId);
		return "Book information saved successfully with Book_ID " + bookId;
	}

	@Override
	@Transactional
	public Book getBookInfo(int bookId) {

		// retrieve book object based on the id supplied in the formal argument
		Book book = (Book) entityManager.find(Book.class, bookId);
		return book;
	}

	@Override
	@Transactional
	public String updateBookInfo(Book updatedBook) {

		// update database with book information and return success msg

		/** If differences are minimal, just getting the book and updating
		 * the field (or a couple fields) is faster than using merge.
		 */

//		Book book = (Book) entityManager.find(Book.class, Integer.valueOf(updatedBook.getBookId()));
//		book.setBookName(updatedBook.getBookName());
//		book.setAuthor(updatedBook.getAuthor());
//		book.setCategory(updatedBook.getCategory());

		/** If there are lots of fields, it's easier to just merge the
		 * updated (detached, new) book with the persisted book and let
		 * the EntityManager take care of checking and updating the fields.
 		 */

		Book newBook = entityManager.merge(updatedBook);

		return "Book information updated successfully";
	}

	@Override
	@Transactional
	public String removeBookInfo(int bookId) {

		// delete book information and return success msg

//		Book book = (Book) entityManager.find(Book.class, bookId);
//		entityManager.remove(book);
//		return "Book with id " + book.getBookId() + " deleted.";

		entityManager.remove((Book) entityManager.find(Book.class, bookId));
		return "Book deleted";

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Book> getAllBookInfo() {

		// get all books info from database
//		List<Book> bookList = sessionFactory.getCurrentSession().createCriteria(Book.class).list();

//		entityManager.createQuery("select * from books");
		List<Book> bookList = entityManager.createQuery("select a from Book a").getResultList();
		return bookList;
	}
}