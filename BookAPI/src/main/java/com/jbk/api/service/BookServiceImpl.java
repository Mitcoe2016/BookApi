package com.jbk.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.api.dao.BookDao;
import com.jbk.api.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao dao;
	int totalBookInSheet = 0;
	@Override
	public boolean saveBooks(Book book) {
		boolean BookIsAdded = dao.saveBooks(book);
		return BookIsAdded;
	}

	@Override
	public Book getBookById(int BookId) {
		Book book = dao.getBookById(BookId);
		return book;
	}

	@Override
	public boolean deleteBookById(int BookId) {

		return dao.deleteBookById(BookId);
	}

	@Override
	public boolean updateBook(Book book) {

		return dao.updateBook(book);
	}

	@Override
	public List<Book> getAllBooks() {

		return dao.getAllBooks();
	}

	@Override
	public List<Book> sortBookByIdAscendingOrder() {

		return dao.sortBookByIdAscendingOrder();
	}

	@Override
	public long getTotalCountOfBooks() {

		return dao.getTotalCountOfBooks();
	}

	@Override
	public List<Book> sortBookByNameAscendingOrder() {
		
		return dao.sortBookByNameAscendingOrder();
	}

	@Override
	public List<Book> sortBookByPriceAscendingOrder() {
		
		return dao.sortBookByPriceAscendingOrder();
	}

	public List<Book> readExcel(String path) {
		Book book = null;
		List<Book> list = new ArrayList<>();
		
		try {

			FileInputStream fis = new FileInputStream(new File(path));

			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheetAt(0);
			totalBookInSheet = sheet.getLastRowNum();
			Iterator<Row> rows = sheet.rowIterator();
			int cnt = 0;

			while (rows.hasNext()) {
				Row row = rows.next();
				book = new Book();
				if (cnt == 0) {
					cnt = cnt + 1;
					continue;
				}

				Iterator<Cell> cells = row.cellIterator();

				while (cells.hasNext()) {

					Cell cell = cells.next();
					int col = cell.getColumnIndex();

					switch (col) {
					case 0: {
						book.setBookId((int) cell.getNumericCellValue());

						break;
					}

					case 1: {
						book.setBookName(cell.getStringCellValue());
						break;
					}

					case 2: {
						book.setBookGenre(cell.getStringCellValue());
						break;
					}

					case 3: {
						book.setBookAuthor(cell.getStringCellValue());
						break;
					}
					case 4: {
						book.setBookPrice(cell.getNumericCellValue());
						break;
					}
					case 5: {
						book.setBookQty((int) cell.getNumericCellValue());
						break;
					}

					default:
						break;
					}

				}

				list.add(book);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	@Override
	public String uploadSheet(MultipartFile file, HttpSession session) {
		int addedCount = 0;
		String path = session.getServletContext().getRealPath("/upload");
		String fileName = file.getOriginalFilename();
		System.out.println(path);
		System.out.println(fileName);

		try {
			byte[] data = file.getBytes();
			FileOutputStream fos = new FileOutputStream(new File(path + File.separator + fileName));
			fos.write(data);

			List<Book> list = readExcel(path + File.separator + fileName);

			for (Book book : list) {
				System.out.println(book);
			}
			addedCount = dao.excelToDb(list);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Total books in sheet=" +totalBookInSheet+"& added Book count "+addedCount ;
	}

	@Override
	public double sumOfBooksPrice() {
		
		return dao.sumOfBooksPrice();
	}

	@Override
	public List<Book> getMaxPriceBook() {
		
		return dao.getMaxPriceBook();
	}

	@Override
	public Book getBookByPriceRange(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
//	public Book getBookByPriceRange(double BookPrice) {
//		
//		return dao.getBookByPriceRange(BookPrice);
//	}
}
