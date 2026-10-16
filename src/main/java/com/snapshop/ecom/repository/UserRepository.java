package com.snapshop.ecom.repository;

import com.snapshop.ecom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

   public User findByEmail(String email);
}
