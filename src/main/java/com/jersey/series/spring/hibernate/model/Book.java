package com.jersey.series.spring.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	// member variables
	@Id 
//	@GeneratedValue
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_generator")
//    @SequenceGenerator(name = "books_id_generator", sequenceName = "books_id_seq", allocationSize = 50)

	@Column(name = "book_id")
	private int bookId;

	@Column(name= "book_name")
	private String bookName;

	@Column(name= "author")
	private String author;

	@Column(name= "category")
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

	@Override
	public String toString() {
		return "Book{" +
				"bookId=" + bookId +
				", bookName='" + bookName + '\'' +
				", author='" + author + '\'' +
				", category='" + category + '\'' +
				'}';
	}
}