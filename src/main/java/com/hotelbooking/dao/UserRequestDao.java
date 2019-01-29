package com.hotelbooking.dao;

import com.hotelbooking.model.User;

import java.util.List;

public interface UserRequestDao {

    void create(User user);

    void update(User user);

    User getUserById(int userId);

    User checkUserByPhone(String phone);

    void delete(int userId);

    List<User> getAllUsers();

}
