package com.library.demo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequestMapping("api/user/")
@RestController
public class UserCtrl {
    @Autowired
    UserRepo userRepo;

    @PostMapping("save")
    public void createUser(@RequestBody User user) {
        userRepo.save(user);
    }

    @GetMapping("all")
    public List<User> showAll() {
        List<User> lista = userRepo.findAll();
        return lista;
    }


    @GetMapping("byName/{name}")
    public List<User> findByName(@PathVariable(value = "name") String name){
        List<User> lista = userRepo.findAllByName(name);
        return lista;
    }


    @GetMapping("byEmail/{email}")
    public List<User> findByEmail(@PathVariable(value = "email") String email){
        List<User> lista = userRepo.findByEmail(email);
        return lista;
    }



}
