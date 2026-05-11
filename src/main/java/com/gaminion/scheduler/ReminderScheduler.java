package com.gaminion.scheduler;

import com.gaminion.note.EntryType;
import com.gaminion.note.NoteEntry;
import com.gaminion.note.NoteEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private NoteEntryRepository noteEntryRepository;

    @Scheduled(fixedRate = 60000)
    public void checkReminders() {
        List<NoteEntry> dueReminders = noteEntryRepository
                .findByTypeAndReminderDateTimeBeforeAndReminderSentFalse(
                        EntryType.REMINDER,
                        LocalDateTime.now()
                );

        for (NoteEntry reminder : dueReminders) {
            System.out.println("REMINDER DUE: [" + reminder.getNotebook().getGame().getName()
                    + "] " + reminder.getTitle());
            reminder.setReminderSent(true);
            noteEntryRepository.save(reminder);
        }
    }
}