//package net.journal.JournalApp.controller;
//
//import net.journal.JournalApp.entity.JournalEntry;
//import net.journal.JournalApp.service.JournalEntryService;
//import org.bson.types.ObjectId;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.*;
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryControllerV2 {
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return journalEntryService.getAll();
//    }
//    @PostMapping
//    public ResponseEntity<JournalEntry> CreateEntry(@RequestBody JournalEntry myEntry){
//        try
//        {
//            myEntry.setDate(LocalDateTime.now());
//            journalEntryService.saveJournalEntry(myEntry);
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
//    @DeleteMapping("/id/{myId}")
//    public  ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId){//? is wildcard...can return anything
//         journalEntryService.deleteJournalEntry(myId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//    @PutMapping("/id/{myId}")
//    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
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
