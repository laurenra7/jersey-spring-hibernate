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

import com.jersey.series.spring.hibernate.dao.BookDAO;
import com.jersey.series.spring.hibernate.db.resources.BookListType;
import com.jersey.series.spring.hibernate.db.resources.BookType;
import com.jersey.series.spring.hibernate.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import in.benchresources.cdm.book.BookListType;
//import in.benchresources.cdm.book.BookType;

@Component
@Path("/books")
public class BookServiceImpl implements IBookService {

	private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookDAO bookDAO;

	// Basic CRUD operations for Book Service

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/addbook
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String createOrSaveBookInfo(BookType bookType) {

		log.info("doing addbook...");

		// unwrap bookType and set in Model object Book
		log.info("new Book()");
		Book newBook = new Book();
		log.info("set book fields...");
		newBook.setBookId(bookType.getBookId());
		newBook.setBookName(bookType.getBookName());
		newBook.setAuthor(bookType.getAuthor());
		newBook.setCategory(bookType.getCategory());
		log.info("bookDAO.insertNewBookInfo()...");
		return bookDAO.insertNewBookInfo(newBook);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getbook/1
	@GET
	@Path("get/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public BookType getBookInfo(@PathParam("id") int bookId) {

		log.info("GET book " + bookId);

		// retrieve book information based on the id supplied in the formal argument
		Book getBook = bookDAO.getBookInfo(bookId);

		BookType bookType = new BookType();
		bookType.setBookId(getBook.getBookId());
		bookType.setBookName(getBook.getBookName());
		bookType.setAuthor(getBook.getAuthor());
		bookType.setCategory(getBook.getCategory());
		return bookType;
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/updatebook
	@PUT
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String updateBookInfo(BookType bookType) {

		// unwrap bookType and set in Model object Book
		Book modifyBook = new Book();
		modifyBook.setBookId(bookType.getBookId());
		modifyBook.setBookName(bookType.getBookName());
		modifyBook.setAuthor(bookType.getAuthor());
		modifyBook.setCategory(bookType.getCategory());

		// update book info & return SUCCESS message
		return bookDAO.updateBookInfo(modifyBook);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/deletebook/5
	@DELETE
	@Path("delete/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String deleteBookInfo(@PathParam("id") int bookId) {

		// delete book info & return SUCCESS message
		Book removeBook = new Book();
		removeBook.setBookId(bookId);
		return bookDAO.removeBookInfo(removeBook);
	}

	// http://localhost:8080/Jersey-Spring-Hibernate/rest/bookservice/getallbook
	@GET
	@Path("getall")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public BookListType getAllBookInfo() {

		log.info("getallbook...");

		List<Book> lstBook = bookDAO.getAllBookInfo();

		// create a object of type BookListType which takes book objects in its list
		BookListType bookListType = new BookListType();

		for(Book book : lstBook){
			BookType bookType = new BookType();
			bookType.setBookId(book.getBookId());
			bookType.setBookName(book.getBookName());
			bookType.setAuthor(book.getAuthor());
			bookType.setCategory(book.getCategory());
			bookListType.getBookType().add(bookType); // add to bookListType
		}
		return bookListType;
	}
}