package ua.example.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ua.example.dao.UserDAO;
import ua.example.models.User;

import java.util.List;

@Primary
@Component
public class HibernateUserDAO implements UserDAO {

    private final SessionFactory sessionFactory;

    public HibernateUserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAll() {
        return currentSession().createQuery("FROM User", User.class).list();
    }

    @Override
    public User getOne(String email) {
        Query<User> query = currentSession().createQuery(
                "FROM User WHERE email =: email", User.class
        );
        query.setParameter("email", email);
        return query.list().stream().findAny().orElse(null);
    }

    @Override
    public void add(User user) {
        currentSession().save(user);
    }
}
