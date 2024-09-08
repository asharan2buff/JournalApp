package net.journal.JournalApp.service;

import net.journal.JournalApp.entity.JournalEntry;
import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.repository.JournalEntryRepo;
import net.journal.JournalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;

    private static final  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);//go to JournalEntryService to use lombok slf4j

    public void saveExistingEntry(User user){
        userRepo.save(user);
    }

    public void saveEntry(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);

    }
    public List<User> getAll(){
        logger.info("Error info");
        logger.warn("Error warn");
        logger.info("Error info");
        logger.debug("Error debug");
        logger.trace("Error trace");
        return userRepo.findAll();
    }
    public void deleteUser(ObjectId myID){
        userRepo.deleteById(myID);
    }
    public Optional<User> findByID(ObjectId myID){
        return  userRepo.findById(myID);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    public User deleteByUsername(String username) {
        return userRepo.deleteByUsername(username);
    }
}
