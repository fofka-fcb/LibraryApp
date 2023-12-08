package ru.myPackage.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.myPackage.models.Book;
import ru.myPackage.models.People;

import java.util.List;

@Component
public class PeopleDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<People> index() {
        return jdbcTemplate.query("SELECT * FROM People WHERE id != 1 ORDER BY id", new BeanPropertyRowMapper<>(People.class));
    }

    public List<People> indexAllPeople() {
        return jdbcTemplate.query("SELECT * FROM People ORDER BY id", new BeanPropertyRowMapper<>(People.class));
    }

    public People show(int id) {
        return jdbcTemplate.query("SELECT * FROM People WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(People.class))
                .stream().findAny().orElse(null);
    }

    public void save(People people) {
        jdbcTemplate.update("INSERT INTO People (full_name, dob) VALUES (?, ?)",
                people.getFullName(),
                people.getDob());
    }

    public void update(People updatePeople, int id) {
        jdbcTemplate.update("UPDATE People SET full_name=?, dob=? WHERE id=?",
                updatePeople.getFullName(),
                updatePeople.getDob(),
                id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM People WHERE id=?", id);
    }

}
