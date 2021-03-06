package ua.example.dao;

import ua.example.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();
    User getOne(String email);
    void add(User user);
}
