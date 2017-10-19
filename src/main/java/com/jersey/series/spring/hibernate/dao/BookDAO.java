package com.jersey.series.spring.hibernate.dao;

import java.util.List;

import com.jersey.series.spring.hibernate.model.Book;

public interface BookDAO {

	public String insertNewBookInfo(Book addBook);
	public Book getBookInfo(int bookId);
	public String updateBookInfo(Book updateBook);
	public String removeBookInfo(int bookId);
	public List<Book> getAllBookInfo();
}