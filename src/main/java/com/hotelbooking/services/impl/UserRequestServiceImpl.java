package com.hotelbooking.services.impl;

import com.hotelbooking.dao.UserRequestDao;
import com.hotelbooking.model.User;
import com.hotelbooking.services.UserRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class UserRequestServiceImpl implements UserRequestService {

    @Autowired
    private UserRequestDao userRequestDao;

    @Override
    public void create(User user) {
        userRequestDao.create(user);
    }

    @Override
    public User getUserByID(int userId) {
       return userRequestDao.getUserById(userId);
    }

    @Override
    public boolean isUserExist(User user) {

        return !user.equals(null);
    }
}
