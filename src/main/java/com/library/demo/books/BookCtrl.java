package com.library.demo.books;

import com.library.demo.borrows.Borrow;
import com.library.demo.borrows.BorrowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/book/")
@RestController
public class BookCtrl {

    @Autowired
    BookRepo bookRepo;


    @PostMapping("save")
    public void createUser(@RequestBody Book book) {
        bookRepo.save(book);
    }

    @GetMapping("all")
    public List<Book> showAll() {
        List<Book> lista = bookRepo.findAll();
        return lista;
    }

}
