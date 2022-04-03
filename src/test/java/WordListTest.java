import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.WordList;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordListTest {
    @Test
    public void storeTodoTest() {
        WordList wordList = new WordList();
        Todo todo = wordList.storeTodo("hahaha", true);
        assertEquals(true, todo.getIsDone());
        assertEquals("hahaha", todo.getDescription());
    }

    @Test
    public void storeDeadlineTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2020,12,12,12,12);
        WordList wordList = new WordList();
        Deadline deadline = wordList.storeDeadline("hahahaha", localDateTime, true);
        assertEquals(true, deadline.getIsDone());
        assertEquals("hahahaha", deadline.getDescription());
        assertEquals(localDateTime, deadline.getDatetime());
    }

    @Test
    public void storeEventTest() {
        LocalDateTime localDateTime = LocalDateTime.of(2020,12,12,12,12);
        WordList wordList = new WordList();
        Event event = wordList.storeEvent("hahahahaha", localDateTime, true);
        assertEquals(true, event.getIsDone());
        assertEquals("hahahahaha", event.getDescription());
        assertEquals(localDateTime, event.getDatetime());
    }
}
