package ua.example.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.example.models.User;

import java.util.List;

@Primary
@Component
public class UserDAOJdbcTemplate implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAOJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public User getOne(String email) {
        return jdbcTemplate.query(
                "SELECT * FROM users WHERE email = ?",
                new Object[] { email },
                new BeanPropertyRowMapper<>(User.class)
        ).stream().findAny().orElse(null);
    }

    public void add(User user) {
        jdbcTemplate.update("INSERT INTO users VALUES(?, ?, ?)",
                user.getName(), user.getSurname(), user.getEmail()
        );
    }
}
