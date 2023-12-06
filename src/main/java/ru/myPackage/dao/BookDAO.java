package ru.myPackage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.myPackage.models.Book;
import ru.myPackage.models.People;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book ORDER BY id", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BookMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book (people_id, name, author, dob) VALUES (?, ?, ?, ?)",
                null,
                book.getName(),
                book.getAuthor(),
                book.getDob());
    }

    public void update(Book updateBook, int id) {
        jdbcTemplate.update("UPDATE Book SET people_id=?, name=?, author=?, dob=? WHERE id=?",
                updateBook.getPeopleID(),
                updateBook.getName(),
                updateBook.getAuthor(),
                updateBook.getDob(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }
}
