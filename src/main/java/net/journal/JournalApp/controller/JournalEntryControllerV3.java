//adding authentication
//package net.journal.JournalApp.controller;
//import net.journal.JournalApp.entity.JournalEntry;
//import net.journal.JournalApp.entity.User;
//import net.journal.JournalApp.service.JournalEntryService;
//import net.journal.JournalApp.service.UserService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryControllerV3 {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return journalEntryService.getAll();
//    }
//
//    @GetMapping("{username}")
//    public ResponseEntity<?> getAllJournalEnteriesOfUser(@PathVariable String username){
//        User user = userService.findByUsername(username);
//        List<JournalEntry> all = user.getJournalEntries();
//        if(all !=null && !all.isEmpty())
//        {
//            return new ResponseEntity<>(all,HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//    @PostMapping("{username}")
//    public ResponseEntity<JournalEntry> CreateEntry(@PathVariable String username,@RequestBody JournalEntry myEntry){
//        try
//        {
//            myEntry.setDate(LocalDateTime.now());
//            journalEntryService.saveJournalEntry(myEntry,username);
//            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
//        }
//        catch (Exception e)
//        {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//    @GetMapping("/id/{myId}")
//    public ResponseEntity<JournalEntry> getJournalById(@PathVariable ObjectId myId){
//        //return journalEntryService.findByID(myId).orElse(null);//since it was optional
//        Optional<JournalEntry> journalEntry = journalEntryService.findByID(myId);
//        if(journalEntry.isPresent())
//            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/id/{username}/{myId}")
//    public  ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId ,@PathVariable String username){
//         journalEntryService.deleteJournalEntry(myId, username);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    @PutMapping("/id/{username}/{myId}")
//    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId,
//                                               @PathVariable String username,
//                                               @RequestBody JournalEntry newEntry){
//        JournalEntry oldEntry = journalEntryService.findByID(myId).orElse(null);
//        if(oldEntry!=null)
//        {
//            oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ?newEntry.getTitle():oldEntry.getTitle());
//            oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ?newEntry.getContent():oldEntry.getContent());
//            journalEntryService.saveJournalEntry(oldEntry);
//            return new ResponseEntity<>(oldEntry, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//}
