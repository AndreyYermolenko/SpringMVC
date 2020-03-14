package ua.example.dao.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ua.example.dao.UserDAO;
import ua.example.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Primary
@Component
@EnableTransactionManagement
@Transactional(readOnly = true)
public class JpaUserDAOImpl implements UserDAO {

    @PersistenceContext(unitName = "emf")
    private EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                "SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Override
    public User getOne(String email) {
        TypedQuery<User> q = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.email =: email", User.class
        );
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
    }
}
