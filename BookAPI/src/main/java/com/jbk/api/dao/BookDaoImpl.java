package com.jbk.api.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.api.entity.Book;

@Repository
public class BookDaoImpl implements BookDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean saveBooks(Book book) {
		boolean BookisAdded = false;
		Session session = sessionFactory.openSession();
		Book book2 = session.get(Book.class, book.getBookId());
		Transaction transaction = session.beginTransaction();
		try {
			if (book != null) {
				session.save(book);
				transaction.commit();
				BookisAdded = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return BookisAdded;

	}

	@Override
	public Book getBookById(int BookId) {
		Book book = null;
		Session session = sessionFactory.openSession();
		try {
			book = session.get(Book.class, BookId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return book;
	}

//	@Override
//	public Book getBookByPriceRange(double minPrice,double maxPrice) {
//		Book book = null;
//		Session session = sessionFactory.openSession();
//		try {
//			book = session.get(Book.class, minPrice,maxPrice);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//		return book;
//	}

	@Override
	public boolean deleteBookById(int BookId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean BookIsDeleted = false;
		try {
			Book book = session.get(Book.class, BookId);
			if (book != null) {
				session.delete(book);
				transaction.commit();
				BookIsDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return BookIsDeleted;
	}

	@Override
	public List<Book> getAllBooks() {
		Session session = sessionFactory.openSession();
		List<Book> list = null;
		try {
			Criteria criteria = session.createCriteria(Book.class);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public List<Book> sortBookByIdAscendingOrder() {
		Session session = sessionFactory.openSession();
		List<Book> sortedList = null;
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.addOrder(Order.asc("BookId"));
			// criteria.addOrder(Order.desc("BookId"));
			sortedList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sortedList;
	}

	@Override
	public long getTotalCountOfBooks() {
		Session session = sessionFactory.openSession();
		long countOfBooks = 0;
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.setProjection(Projections.rowCount());
			List<Long> list = criteria.list();
			if (!list.isEmpty()) {
				countOfBooks = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return countOfBooks;
	}

	@Override
	public List<Book> sortBookByNameAscendingOrder() {
		Session session = sessionFactory.openSession();
		List<Book> sortedList = null;
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.addOrder(Order.asc("BookName"));
			// criteria.addOrder(Order.desc("BookName"));
			sortedList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sortedList;

	}

	@Override
	public List<Book> sortBookByPriceAscendingOrder() {
		Session session = sessionFactory.openSession();
		List<Book> sortedList = null;
		try {
			Criteria criteria = session.createCriteria(Book.class);
			criteria.addOrder(Order.asc("BookPrice"));
			// criteria.addOrder(Order.desc("BookPrice"));
			sortedList = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return sortedList;

	}

	@Override
	public int excelToDb(List<Book> list) {
		int addedCount = 0;
		for (Book book : list) {
			boolean isAdded = saveBooks(book);
			if (isAdded) {
				addedCount = addedCount + 1;
			}
		}
		return addedCount;
	}

	@Override
	public double sumOfBooksPrice() {
		Session openSession = sessionFactory.openSession();
		double sum = 0;
		Criteria criteria = openSession.createCriteria(Book.class);
		criteria.setProjection(Projections.sum("BookPrice"));
		List<Double> list = criteria.list();
		try {
			if (!list.isEmpty()) {
				sum = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (openSession != null) {
				openSession.close();
			}
		}
		return sum;

	}

	@Override
	public List<Book> getMaxPriceBook() {
		Session session = sessionFactory.openSession();
		double maxPrice = 0;

		List<Book> maxBook = null;
		try {
			Criteria maxcriteria = session.createCriteria(Book.class);
			maxcriteria.setProjection(Projections.max("BookPrice"));
			// maxcriteria.setProjection(Projections.min("BookPrice"));->for minimum
			// price
			List<Double> list = maxcriteria.list();// fetching that max price value& stored in List<Double>list, bcs
													// there may be many same max price Book
			// list.get(0)->to fetch that max price value
			maxPrice = list.get(0);

			Criteria eqcriteria = session.createCriteria(Book.class);
			eqcriteria.add(Restrictions.eq("BookPrice", maxPrice));

			maxBook = eqcriteria.list();

			if (!maxBook.isEmpty()) {
				System.out.println(maxBook);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return maxBook;
	}

	@Override
	public boolean updateBook(Book book) {

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean BookIsUpdated = false;
		try {
			Book book2 = session.get(Book.class, book.getBookId());
			session.evict(book2);
			session.update(book);
			transaction.commit();
			BookIsUpdated = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return BookIsUpdated;
	}

	@Override
	public Book getBookByPriceRange(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Book getBookByPriceRange(double minPrice, double maxPrice) {
//		Session session = sessionFactory.openSession();
//		Book book=null;
//		try {
//			book = session.get(Book.class, session)
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			if (session != null) {
//				session.close();
//			}
//		}
//		return book;
//	}

}
