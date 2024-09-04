package net.journal.JournalApp.repository;

//import net.journal.JournalApp.entity.JournalEntry;
import net.journal.JournalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId>{

    User findByUsername(String username);
    User deleteByUsername(String username);

}
