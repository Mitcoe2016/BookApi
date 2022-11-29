package com.jbk.api.dao;

import java.util.List;

import com.jbk.api.entity.Book;

public interface BookDao {
	public boolean saveBooks(Book book);
	public Book getBookById(int BookId);
	public boolean deleteBookById(int BookId);
	public boolean updateBook(Book book);
	public List<Book> getAllBooks();
	public List<Book> sortBookByIdAscendingOrder();
	public long getTotalCountOfBooks();
	public double sumOfBooksPrice();
	public List<Book> getMaxPriceBook();
	public List<Book> sortBookByNameAscendingOrder();
	public List<Book> sortBookByPriceAscendingOrder();
	public int excelToDb(List<Book> list);
	public Book getBookByPriceRange(double minPrice, double maxPrice);
}
