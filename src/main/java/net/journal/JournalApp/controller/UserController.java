package net.journal.JournalApp.controller;

import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        try
        {
            userService.saveEntry(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("/id/{myId}")
//    public ResponseEntity<User> getUserById(@PathVariable ObjectId myId){
//        Optional<User> user = userService.findByID(myId);
//        if(user.isPresent())
//            return new ResponseEntity<>(user.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public  ResponseEntity<?> deleteUser(@PathVariable ObjectId myId){
//         userService.deleteUser(myId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb = userService.findByUsername(userName);
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

}
