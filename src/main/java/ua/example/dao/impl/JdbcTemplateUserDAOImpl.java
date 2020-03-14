package ua.example.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.example.dao.UserDAO;
import ua.example.model.User;

import java.util.List;

@Component
public class JdbcTemplateUserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserDAOImpl(JdbcTemplate jdbcTemplate) {
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
