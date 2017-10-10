package com.jersey.series.spring.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book {

	// member variables
	@Id 
	@GeneratedValue
	@Column(name = "BOOK_ID")
	private int bookId;

	@Column(name= "BOOK_NAME")
	private String bookName;

	@Column(name= "AUTHOR")
	private String author;

	@Column(name= "CATEGORY")
	private String category;

	// getters & setters
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}