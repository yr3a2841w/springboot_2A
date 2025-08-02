package com.college.yi.bookmanager.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.college.yi.bookmanager.model.Book;
import com.college.yi.bookmanager.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(BookController.class)
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;
	
	private Book book;
	
	@Autowired
	private ObjectMapper objectMapper;
	
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
		
		mockMvc.perform(get("/api/books"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].title").value("タイトル"));
		
		verify(bookService).getBooks();
	}
	
	@Test
	void testCreateBooks() throws Exception{
		when(bookService.createBook(any(Book.class))).thenReturn(book);
		
		mockMvc.perform(post("/api/books")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("タイトル"));
		
		verify(bookService).createBook(any(Book.class));
		
	}
	
	@Test
	void testUpdateBooks() throws Exception{
		when(bookService.updateBook(eq(1),any(Book.class))).thenReturn(book);
		
		mockMvc.perform(put("/api/books/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(book)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.title").value("タイトル"));
		
		verify(bookService).updateBook(eq(1),any(Book.class));
		
	}
	
	@Test
	void testDeleteBooks() throws Exception{
		doNothing().when(bookService).deleteBook(1);
		
		mockMvc.perform(delete("/api/books/1"))
				.andExpect(status().isOk());
				
		verify(bookService).deleteBook(1);
		
	}
	
}
