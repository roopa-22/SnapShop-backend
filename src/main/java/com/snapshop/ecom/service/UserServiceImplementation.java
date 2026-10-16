package com.snapshop.ecom.service;

import com.snapshop.ecom.config.JwtProvider;
import com.snapshop.ecom.exception.UserException;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        }
        throw new UserException("User not found with id : "+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailfromToken(jwt);

        User user = userRepository.findByEmail(email);

        if(user == null) {
            throw new UserException("user not found witj email : "+email);
        }
        return user;
    }
}
