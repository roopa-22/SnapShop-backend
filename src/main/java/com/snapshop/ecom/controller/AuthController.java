package com.snapshop.ecom.controller;

import com.snapshop.ecom.config.JwtProvider;
import com.snapshop.ecom.exception.UserException;
import com.snapshop.ecom.model.Cart;
import com.snapshop.ecom.model.User;
import com.snapshop.ecom.repository.UserRepository;
import com.snapshop.ecom.request.LoginRequest;
import com.snapshop.ecom.response.AuthResponse;
import com.snapshop.ecom.service.CartService;
import com.snapshop.ecom.service.CustomUserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserServiceImplementation customUserService;

    @Autowired
    private CartService cartService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws UserException {
       String email = user.getEmail();
       String password = user.getPassword();
       String firstName = user.getFirstName();
       String lastName = user.getLastName();

       User isEmailExists = userRepository.findByEmail(email);

       if(isEmailExists != null) {
           throw new UserException("Email Already exists!");
       }

       User createdUser = new User();
       createdUser.setEmail(email);
       createdUser.setPassword(passwordEncoder.encode(password));
       createdUser.setFirstName(firstName);
       createdUser.setLastName(lastName);

       User saveduser = userRepository.save(createdUser);

        Cart cart = cartService.createCart(saveduser);

       Authentication authentication = new UsernamePasswordAuthenticationToken(saveduser.getEmail(),saveduser.getPassword());
       SecurityContextHolder.getContext().setAuthentication(authentication);

       String token = jwtProvider.generateToken(authentication);

       AuthResponse authResponse = new AuthResponse();
       authResponse.setJwt(token);
       authResponse.setMessage("Signup Successful!");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse>loginUserHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("SignIn Successful!");

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
         UserDetails userDetails = customUserService.loadUserByUsername(username);

         if(userDetails == null) {
             throw new BadCredentialsException("Invalid Username");
         }

         if(!passwordEncoder.matches(password,userDetails.getPassword())) {
             throw new BadCredentialsException("Invalid Password");
         }

         return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}

