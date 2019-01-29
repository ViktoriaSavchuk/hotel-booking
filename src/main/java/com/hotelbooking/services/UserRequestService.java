package com.hotelbooking.services;

import com.hotelbooking.model.User;

public interface UserRequestService {
    void create(User user);

    User getUserByID(int userId);

    boolean isUserExist(User user);


}
