package com.college.yi.bookmanager.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.college.yi.bookmanager.entity.BookEntity;
import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.repository.BookRepository;

	@ExtendWith(MockitoExtension.class)
	class BookServiceTest {
		
		@Mock
		private BookRepository bookRepository;
		
		@InjectMocks
		private BookService bookService;
		
		private BookEntity bookEntity;
		private Book book;
		
		@BeforeEach
		void setUp() {
			
			book=new Book();
			book.setId(1);
			book.setTitle("タイトル");
			book.setAuthor("著者");
			book.setPublisher("出版社");
			book.setPublishedDate(LocalDate.of(2025, 7, 24));
			book.setStock(1);
			
			bookEntity=new BookEntity();
			bookEntity.setId(book.getId());
			bookEntity.setTitle(book.getTitle());
			bookEntity.setAuthor(book.getAuthor());
			bookEntity.setPublisher(book.getPublisher());
			bookEntity.setPublishedDate(book.getPublishedDate());
			bookEntity.setStock(book.getStock());
			
		}
		
		@Test
		void testGetBooks() {
			
			when(bookRepository.findAllBook()).thenReturn(List.of(bookEntity));
			
			List<Book> result=bookService.getBooks();
			
			assertEquals(1,result.size());
			assertEquals("タイトル",result.get(0).getTitle());
			assertEquals("著者",result.get(0).getAuthor());
			assertEquals("出版社",result.get(0).getPublisher());
			assertEquals(LocalDate.of(2025, 7, 24),result.get(0).getPublishedDate());
			assertEquals(1,result.get(0).getStock());
			verify(bookRepository).findAllBook();
		}
		
		@Test
		void testCreateBooks() {
			
			doNothing().when(bookRepository).insert(any(BookEntity.class));
			
			Book result=bookService.createBook(book);
			
			assertEquals("タイトル",result.getTitle());
			verify(bookRepository).insert(any(BookEntity.class));
			
		}
		
		@Test
		void testUpdateBooks() {
			
			doNothing().when(bookRepository).update(any(BookEntity.class));
			
			Book result=bookService.createBook(book);
			
			assertEquals(1,result.getId());
			verify(bookRepository).update(any(BookEntity.class));
			
		}

		@Test
		void testDeleteBook() {
			
			doNothing().when(bookRepository).delete(bookEntity.getId());
			
			bookService.deleteBook(1);
			verify(bookRepository).delete(bookEntity.getId());
			
		}
		
	}


