package net.journal.JournalApp.scheduler;

import net.journal.JournalApp.entity.JournalEntry;
import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.repository.UserRepositoryImpl;
import net.journal.JournalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Scheduled(cron = "* * * * * *")
    public void fetchUsersAndSendSaEmail(){
        List<User> users = userRepository.getUserForSA();
        for(User user : users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEnteries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEnteries);
            String sentiment = "Happy";
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment+" For enteries "+entry);
        }
    }

}
