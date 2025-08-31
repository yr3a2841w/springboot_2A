package com.college.yi.bookmanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.college.yi.bookmanager.entity.BookEntity;
import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository repository;
	
	public BookService(BookRepository repository) {
		this.repository=repository;
	}
	
	public List<Book> getBooks(){
		List<BookEntity> entity= repository.findAllBook();
		if(entity.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}else {
			return entity.stream().map(this::convertModel).collect(Collectors.toList());
		}
	}
	
	public Book createBook(Book modelBook) {
		BookEntity entityBook=convertEntity(modelBook);
		repository.insert(entityBook);
		return modelBook;
	}
	
	public Book updateBook(int id,Book modelBook) {
		BookEntity entityBook=convertEntity(modelBook);
		entityBook.setId(id);
		repository.update(entityBook);
		return modelBook;
	}
	
	public void deleteBook(int id) {
		repository.delete(id);
	}
	
	public List<Book> searchBooks(String title, String author, String publisher, Integer minStock, String publishedDate) {
	    
	    if (title != null && title.isEmpty()) title = null;
	    if (author != null && author.isEmpty()) author = null;
	    if (publisher != null && publisher.isEmpty()) publisher = null;

	    String publishedDatePattern = null;
	    if (publishedDate != null && !publishedDate.isEmpty()) {
	        if (publishedDate.matches("\\d{4}")) {
	            publishedDatePattern = "yyyy";
	        } else if (publishedDate.matches("\\d{4}-\\d{2}")) {
	            publishedDatePattern = "yyyy-MM";
	        } else if (publishedDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
	            publishedDatePattern = "yyyy-MM-dd";
	        } else {
	            throw new IllegalArgumentException("出版日形式が不正です");
	        }
	    }

	    List<BookEntity> searchResult = repository.searchBooks(title, author, publisher, minStock, publishedDate, publishedDatePattern);
	    return searchResult.stream().map(this::convertModel).collect(Collectors.toList());
	}
	
	public BookEntity convertEntity(Book modelBook) {
		BookEntity entityBook=new BookEntity();
		entityBook.setId(modelBook.getId());
		entityBook.setTitle(modelBook.getTitle());
		entityBook.setAuthor(modelBook.getAuthor());
		entityBook.setPublisher(modelBook.getPublisher());
		entityBook.setPublishedDate(modelBook.getPublishedDate());
		entityBook.setStock(modelBook.getStock());
		
		return entityBook;
	}
		
	public Book convertModel(BookEntity entityBook) {
		Book modelBook=new Book();
		modelBook.setId(entityBook.getId());
		modelBook.setTitle(entityBook.getTitle());
		modelBook.setAuthor(entityBook.getAuthor());
		modelBook.setPublisher(entityBook.getPublisher());
		modelBook.setPublishedDate(entityBook.getPublishedDate());
		modelBook.setStock(entityBook.getStock());	
		
		return modelBook;
	}
	
}
