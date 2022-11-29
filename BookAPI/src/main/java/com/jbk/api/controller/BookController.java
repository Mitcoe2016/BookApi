package com.jbk.api.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.entity.Book;
import com.jbk.api.exception.BookAlreadyExistException;
import com.jbk.api.exception.BooktNotFound;
import com.jbk.api.service.BookService;

@RestController
public class BookController {
	@Autowired
	private BookService service;
        private BookAlreadyExistException bookalreadyexistexception; 
        
	@PostMapping(value = "saveBooks")
	public ResponseEntity<Boolean> saveBooks(@Valid @RequestBody Book book) {
		boolean BookIsAdded = service.saveBooks(book);
		System.out.println(book);

		if (BookIsAdded) {
			return new ResponseEntity<Boolean>(BookIsAdded, HttpStatus.CREATED);
		} else {
			throw new BookAlreadyExistException("Book already exist with Id="  + book.getBookId());
		}
	}
	
//private BooktNotFound booktNotFound;
private NullPointerException nullPointerException;

	@GetMapping(value = "getBookById")
	public ResponseEntity<Book> getBookById(@RequestParam int BookId) {
		Book book = service.getBookById(BookId);
		if (book != null) {
			return new ResponseEntity<Book>(book, HttpStatus.FOUND);
		} else {
			throw new NullPointerException("Book with id= " + book.getBookId() + " not found");
		}

	}

//		@GetMapping(value = "getBookByPriceRange")
//		public ResponseEntity<Book> getBookByPriceRange(@RequestParam double minPrice,@RequestParam double maxPrice) {
//			Book book = service.getBookByPriceRange(minPrice,maxPrice);
//			if (book != null) {
//				return new ResponseEntity<Book>(book, HttpStatus.FOUND);
//			} else {
//				return new ResponseEntity<Book>(book, HttpStatus.NOT_FOUND);
//			}
//
//	}

	@DeleteMapping(value = "deleteBookById")
	public ResponseEntity<Boolean> deleteBookById(@RequestParam int BookId) {
		boolean BookIsDeleted = service.deleteBookById(BookId);
		if (BookIsDeleted) {
			return new ResponseEntity<Boolean>(BookIsDeleted, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Boolean>(BookIsDeleted, HttpStatus.OK);
		}

	}

	@PutMapping(value = "updateBook")
	public ResponseEntity<Boolean> updateBook(@Valid @RequestBody Book book) {
		boolean BookIsUpdated = service.updateBook(book);

		return new ResponseEntity<Boolean>(BookIsUpdated, HttpStatus.OK);

	}

	@GetMapping(value = "getAllBooks")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> list = service.getAllBooks();
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
		}

	}

	@GetMapping(value = "sortBookByIdAscendingOrder")
	public ResponseEntity<List<Book>> sortBookByIdAscendingOrder() {
		List<Book> sortedList = service.sortBookByIdAscendingOrder();
		if (!sortedList.isEmpty()) {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		}

	}

	@GetMapping(value = "sortBookByNameAscendingOrder")
	public ResponseEntity<List<Book>> sortBookByNameAscendingOrder() {
		List<Book> sortedList = service.sortBookByNameAscendingOrder();
		if (!sortedList.isEmpty()) {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		}

	}

	@GetMapping(value = "sortBookByPriceAscendingOrder")
	public ResponseEntity<List<Book>> sortBookByPriceAscendingOrder() {
		List<Book> sortedList = service.sortBookByPriceAscendingOrder();
		if (!sortedList.isEmpty()) {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Book>>(sortedList, HttpStatus.OK);
		}

	}

	@GetMapping(value = "countOfBooks")
	public ResponseEntity<Long> getTotalCountOfBooks() {

		long countOfBooks = service.getTotalCountOfBooks();

		return new ResponseEntity<Long>(countOfBooks, HttpStatus.OK);

	}

	@PostMapping(value = "uploadsheet")
	public ResponseEntity<String> uploadSheet(@RequestParam MultipartFile file, HttpSession session) {

		String path = session.getServletContext().getRealPath("/uploaded");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		System.out.println(fileName);

		String msg = service.uploadSheet(file, session);

		return new ResponseEntity<String>(msg, HttpStatus.OK);

	}

	@GetMapping(value = "sumOfBooksPrice")
	public ResponseEntity<Double> sumOfBooksPrice() {
		double sum = service.sumOfBooksPrice();
		return new ResponseEntity<Double>(sum, HttpStatus.OK);

	}

	@GetMapping(value = "getMaxPriceBook")
	public ResponseEntity<List<Book>> getMaxPriceBook() {
		List<Book> getMaxPriceBook = service.getMaxPriceBook();

		return new ResponseEntity<List<Book>>(getMaxPriceBook, HttpStatus.OK);
	}
}
