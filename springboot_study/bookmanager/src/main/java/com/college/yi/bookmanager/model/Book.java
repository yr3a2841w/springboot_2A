package com.college.yi.bookmanager.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Book {
	
	private int id;
	private String title;
	private String author;
	private String publisher;
	private LocalDate publishedDate;
	private int stock;
	

}
