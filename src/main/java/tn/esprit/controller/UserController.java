package tn.esprit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.DAO.UserDAO;
import tn.esprit.entity.User;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", maxAge=0)
public class UserController {

    @Autowired
    UserDAO userDAO;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @GetMapping("/byid/{id}")
    public User getUserById(@PathVariable Integer id) {
        Optional<User> u = userDAO.findById(id);
        if(!u.isPresent()) return null;
        return u.get();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userDAO.saveAndFlush(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Integer id,@RequestBody User user) {
        Optional<User> u = userDAO.findById(id);
        if(!u.isPresent()) return null;
        User ul = u.get();
        ul.setAge(user.getAge());
        ul.setNom(user.getNom());
        ul.setPrenom(user.getPrenom());
        ul.setSexe(user.getSexe());
        ul.setTel(user.getTel());
        return userDAO.saveAndFlush(ul);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        Optional<User> u = userDAO.findById(id);
        if(!u.isPresent()) return "User not exist";
        userDAO.delete(u.get());
        userDAO.flush();
        return "Done";
    }
}
