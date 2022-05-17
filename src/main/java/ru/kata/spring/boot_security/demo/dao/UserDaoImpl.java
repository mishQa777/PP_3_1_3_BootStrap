package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user, int id) {
        entityManager.merge(user);
    }

//    @Override
//    public void update(User user, int id) {
//        System.out.println(user.toString());
//        entityManager.createQuery("update User set name = :name, surname = :surname, age = :age," +
//                        " username = :username, password = :password, roles =:roles  where id = :id")
//                .setParameter("name", user.getName())
//                .setParameter("surname", user.getSurname())
//                .setParameter("age", user.getAge())
//                .setParameter("username", user.getUsername())
//                .setParameter("password", user.getPassword())
//                .setParameter("id",  user.getId())
//                .setParameter("roles",  user.getRoles())
//                .executeUpdate();
//    }

    @Override
    public void delete(int id) {
        entityManager.createQuery("delete from User where id= :id")
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery("select user from User user where user.username = :username", User.class);
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
