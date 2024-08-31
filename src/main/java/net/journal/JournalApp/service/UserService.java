package net.journal.JournalApp.service;

import net.journal.JournalApp.entity.JournalEntry;
import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.repository.JournalEntryRepo;
import net.journal.JournalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public void saveEntry(User user){
        userRepo.save(user);
    }
    public List<User> getAll(){
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
}
