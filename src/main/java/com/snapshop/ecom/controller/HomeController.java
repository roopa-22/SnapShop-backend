package com.snapshop.ecom.controller;

import com.snapshop.ecom.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse> homeController() {
        ApiResponse res = new ApiResponse();
        res.setMessage("Welcome to SnapShop API");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
