package com.college.yi.bookmanager.controller;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@WebMvcTest(BookController.class)
public class BookControllerTest {

	private MockMvc mockMvc;
	
	@MockBean
	private BookService bookService;
	
	private Book book;
	
	@BeforeEach
	void setUp(){
		
		book=new Book();
		book.setId(1);
		book.setTitle("タイトル");
		book.setAuthor("著者");
		book.setPublisher("出版社");
		book.setPublishedDate(LocalDate.of(2025, 7, 28));
		book.setStock(1);
		
	}
	
	@Test
	void testGetBooks() throws Exception{
		when(bookService.getBooks()).thenReturn(List.of(book));
	}
	
}
