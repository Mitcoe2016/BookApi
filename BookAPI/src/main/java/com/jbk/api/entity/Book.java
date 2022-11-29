package com.jbk.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Book {
	@Id
	@Min(1)
	private int BookId;
	@NotNull(message = "Book name is required")
	private String BookName;
	@NotNull(message = "Book Genre name is required")
	private String BookGenre;
	@NotNull(message = "Book Author name is required")
	private String BookAuthor;
	@Min(1)
	private double BookPrice;
	@Min(1)
	private int BookQty;

	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public Book(int bookId, String bookName, String bookGenre, String bookAuthor, double bookPrice, int bookQty) {
		super();
		BookId = bookId;
		BookName = bookName;
		BookGenre = bookGenre;
		BookAuthor = bookAuthor;
		BookPrice = bookPrice;
		BookQty = bookQty;
	}

	

	public int getBookId() {
		return BookId;
	}

	public void setBookId(int bookId) {
		BookId = bookId;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookGenre() {
		return BookGenre;
	}

	public void setBookGenre(String bookGenre) {
		BookGenre = bookGenre;
	}

	public String getBookAuthor() {
		return BookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}

	public double getBookPrice() {
		return BookPrice;
	}

	public void setBookPrice(double bookPrice) {
		BookPrice = bookPrice;
	}

	public int getBookQty() {
		return BookQty;
	}

	public void setBookQty(int bookQty) {
		BookQty = bookQty;
	}

	@Override
	public String toString() {
		return "Book [BookId=" + BookId + ", BookName=" + BookName + ", BookGenre=" + BookGenre + ", BookAuthor="
				+ BookAuthor + ", BookPrice=" + BookPrice + ", BookQty=" + BookQty + "]";
	}
}
