package net.journal.JournalApp.service;

import net.journal.JournalApp.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private JournalEntryService journalEntryService;

    @Test
    public void testAdd(){

        assertNotNull(userRepository.findByUsername("ashu"));
    }

    @ParameterizedTest
    @CsvSource({
            "satyam",
            "ashu"
    })
    public void test(String username){
        assertNotNull(userRepository.findByUsername(username));
    }

}