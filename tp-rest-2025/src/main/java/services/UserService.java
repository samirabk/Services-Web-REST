package services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.User;
import java.util.List;

@RequestScoped // Annotation CDI
public class UserService {

    @PersistenceContext(unitName = "tp-jakartaee")
    private EntityManager em;

    @Transactional // Obligatoire en CDI pour écrire en base
    public Long createUser(User user) {
        em.persist(user);
        return user.getId();
    }

    public User findUserById(Long id) {
        return em.find(User.class, id);
    }

    public List<User> findAllUsers() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }
}