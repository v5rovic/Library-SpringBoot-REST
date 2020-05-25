package com.library.demo.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    List<User> findByEmail(String email);
    List<User> findAllByName(String name);


}
