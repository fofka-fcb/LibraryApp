package ru.myPackage.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.myPackage.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {

        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setPeopleID(rs.getInt("people_id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setDob(rs.getString("dob"));

        return book;
    }

}
