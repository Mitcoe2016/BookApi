package com.jbk.api.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Book;

public interface BookService {
	public boolean saveBooks(Book book);
	public Book getBookById(int BookId);
	public boolean deleteBookById(int BookId);
	public boolean updateBook(Book book);
	public List<Book> getAllBooks();
	public List<Book> sortBookByIdAscendingOrder();
	public long getTotalCountOfBooks();
	public double sumOfBooksPrice();
	public List<Book> sortBookByNameAscendingOrder();
	public List<Book> sortBookByPriceAscendingOrder();
	public String uploadSheet(MultipartFile file, HttpSession session);
	public List<Book> getMaxPriceBook();
	//public Book getBookByPriceRange(double minPrice,double maxPrice);
	public Book getBookByPriceRange(double minPrice, double maxPrice);
}
