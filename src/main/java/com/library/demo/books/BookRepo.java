package com.library.demo.books;

import com.library.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {

    List<User> findByName(String name);

}
