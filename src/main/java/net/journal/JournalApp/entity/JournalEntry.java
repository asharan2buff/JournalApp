package net.journal.JournalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//includes all getter, setter etc
//used for deserialisation
//@Setter
//@Getter

@Document(collection = "journal_entries")//name of collection. if we dont specify, will search for JournalEntry collection
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;//can give id...else mongo will give id
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
