package net.journal.JournalApp.service;

import net.journal.JournalApp.entity.JournalEntry;
import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional//to achieve atomicity..everything is considered 1 op...if anything fails...everything rolled back.
    public void saveJournalEntry(JournalEntry journalEntry, String username){
        User user = userService.findByUsername(username);
        journalEntry.setDate(LocalDateTime.now());

        JournalEntry saved = journalEntryRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
//        user.setUsername(null);//if this is null then below will fail but still journal entry will be added.
        userService.saveEntry(user);

    }

    public void saveJournalEntry(JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }
    public void deleteJournalEntry(ObjectId myID, String username){
        User user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(x->x.getId().equals(myID));//done becoz in user db, ref of deleted ob is still present. to remove that, we need to do cascade delete
        userService.saveEntry(user);
        journalEntryRepo.deleteById(myID);
    }
    public Optional<JournalEntry> findByID(ObjectId myID){
        return  journalEntryRepo.findById(myID);
    }
}
