package net.journal.JournalApp.controller;

import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUser(){
        return userService.getAll();
    }

    @PutMapping//in basic auth give username and password
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//spring security already has username. No need to pass it explicitly
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @DeleteMapping//in basic auth give username and password
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//spring security already has username. No need to pass it explicitly
        String userName = authentication.getName();
        userService.deleteByUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
