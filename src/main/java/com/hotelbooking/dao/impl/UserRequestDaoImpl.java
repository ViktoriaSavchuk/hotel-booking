package com.hotelbooking.dao.impl;

import com.hotelbooking.dao.UserRequestDao;
import com.hotelbooking.model.Room;
import com.hotelbooking.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.List;

@Repository
public class UserRequestDaoImpl implements UserRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User user) {

      //  if (getUserByPhone(user.getPhone()) == null && getUserById(user.getId()) == null) {
            entityManager.persist(user);
      //  }
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public User checkUserByPhone(String phone) {

        Query query=entityManager.createNamedQuery("SELECT USERS.NAME, USERS.SURNAME, USERS.PHONE FROM USERS WHERE USERS.PHONE="+phone, User.class);

        List<User> users=query.getResultList();

        return users.get(0);

       /* List<User> users=getAllUsers();
        for(User user: users){
            if (user.getPhone().equals(phone)) return user;
        }
        return null;*/
    }

    @Override
    public void delete(int userId) {
        User user = getUserById(userId);

        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery=criteriaBuilder.createQuery(User.class);

        Root<User> from = criteriaQuery.from(User.class);

        CriteriaQuery<User> all =criteriaQuery.select(from);
        TypedQuery<User> allQuery =entityManager.createQuery(all);

        return allQuery.getResultList();
    }
}
