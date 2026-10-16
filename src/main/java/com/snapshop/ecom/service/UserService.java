package com.snapshop.ecom.service;

import com.snapshop.ecom.exception.UserException;
import com.snapshop.ecom.model.User;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}

