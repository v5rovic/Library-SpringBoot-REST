package com.library.demo.borrows;

import com.library.demo.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRepo extends JpaRepository<Borrow, Long> {

    List<Borrow> findByUid(Long uid);
    List<Borrow> findByBid(Long bid);

}
