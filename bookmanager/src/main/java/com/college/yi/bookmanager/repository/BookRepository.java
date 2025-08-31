package com.college.yi.bookmanager.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.college.yi.bookmanager.entity.BookEntity;

@Mapper
public interface BookRepository {

	@Select("SELECT id,title,author,publisher,published_date AS publishedDate,stock FROM books")
	List<BookEntity> findAllBook();
	
	@Select("SELECT id,title,author,publisher,published_date AS publishedDate,stock FROM books WHERE id=#{id}")
	BookEntity findById(int id);
	
	@Insert("INSERT INTO books(title,author,publisher,published_date,stock) values (#{title},#{author},#{publisher},#{publishedDate},#{stock})")
	void insert(BookEntity book);
	
	@Update("UPDATE books SET title=#{title},author=#{author},publisher=#{publisher},published_date=#{publishedDate},stock=#{stock} WHERE id=#{id}")
	void update(BookEntity book);
	
	@Delete("DELETE FROM books WHERE id=#{id}")
	void delete(int id);
	
	@Select({
        "<script>",
        "SELECT id, title, author, publisher, published_date AS publishedDate, stock",
        "FROM books",
        "WHERE 1=1",
        "<if test='title != null and title != \"\"'>",
        "   AND title LIKE CONCAT('%', #{title}, '%')",
        "</if>",
        "<if test='author != null and author != \"\"'>",
        "   AND author LIKE CONCAT('%', #{author}, '%')",
        "</if>",
        "<if test='publisher != null and publisher != \"\"'>",
        "   AND publisher LIKE CONCAT('%', #{publisher}, '%')",
        "</if>",
        "<if test='stock != null'>",
        "   AND stock >= #{stock}",
        "</if>",
        "<if test='publishedDate != null and publishedDatePattern != null'>",
        "   <choose>",
        "       <when test='publishedDatePattern == \"yyyy\"'>",
        "           AND TO_CHAR(published_date, 'YYYY') = #{publishedDate}",
        "       </when>",
        "       <when test='publishedDatePattern == \"yyyy-MM\"'>",
        "           AND TO_CHAR(published_date, 'YYYY-MM') = #{publishedDate}",
        "       </when>",
        "       <when test='publishedDatePattern == \"yyyy-MM-dd\"'>",
        "           AND TO_CHAR(published_date, 'YYYY-MM-DD') = #{publishedDate}",
        "       </when>",
        "   </choose>",
        "</if>",
        "</script>"
    })
    List<BookEntity> searchBooks(
        @Param("title") String title,
        @Param("author") String author,
        @Param("publisher") String publisher,
        @Param("stock") Integer stock,
        @Param("publishedDate") String publishedDate,
        @Param("publishedDatePattern") String publishedDatePattern
    );
	
	
}