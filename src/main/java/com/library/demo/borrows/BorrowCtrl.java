package com.library.demo.borrows;


import com.library.demo.Exc.ResourceNotAvailable;
import com.library.demo.Exc.ResourceNotFoundException;
import com.library.demo.books.Book;
import com.library.demo.books.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RequestMapping("api/borrow/")
@RestController
public class BorrowCtrl {

    @Autowired
    BorrowRepo borrowRepo;
    @Autowired
    BookRepo bookRepo;

    @PostMapping("save")
    public void createBorrowing(@RequestBody Borrow brw) {

        Book book = bookRepo.findById(brw.getBid()).orElseThrow(() -> new ResourceNotFoundException("Book", "id", brw.getBid()));
        if(book.getAvailable() > 0){
            book.setAvailable(book.getAvailable()-1);

            bookRepo.save(book);

            borrowRepo.save(brw);
        }else{
            throw new ResourceNotAvailable("Book", "id", brw.getBid());
        }

    }

    @GetMapping("all")
    public List<Borrow> showAll() {
        List<Borrow> lista = borrowRepo.findAll();
        return lista;
    }

    @DeleteMapping("delete/{id}")
    public void deleteBorrowing(@PathVariable(value = "id") Long id) {
        Borrow brw = borrowRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Borrow", "id", id));
        borrowRepo.delete(brw);
    }

    @PutMapping("return/{id}")
    public void returnBook(@PathVariable(value = "id") Long id) {

        Borrow brw = borrowRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow", "id", id));
        if(brw.getRdate() == null){
            Book book = bookRepo.findById(brw.getBid()).orElseThrow(() -> new ResourceNotFoundException("Book", "id", brw.getBid()));

            book.setAvailable(book.getAvailable()+1);

            bookRepo.save(book);

            brw.setRdate(new Date());

            borrowRepo.save(brw);
        }else{
            throw new RuntimeException("Book with id: " + brw.getBid() + " is returned already");

        }




    }
}
