//package net.journal.JournalApp.controller;
//
//import net.journal.JournalApp.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {
//
//    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//    @PostMapping
//    public boolean CreateEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//    }
//    @GetMapping("/id/{myId}")
//    public JournalEntry getJournalById(@PathVariable long myId){
//        return journalEntries.get(myId);
//    }
//
//    @DeleteMapping("/id/{myId}")
//    public  JournalEntry deleteJournalById(@PathVariable long myId){
//        return journalEntries.remove(myId);
//    }
//    @PutMapping("/id/{myId}")
//    public JournalEntry updateJournalById(@PathVariable long myId, @RequestBody JournalEntry myEntry){
//        return journalEntries.put(myId, myEntry);
//    }
//
//}
