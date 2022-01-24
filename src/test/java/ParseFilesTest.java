import Taskmaster.Exception.DukeExceptions;
import Taskmaster.Task.Task;
import Taskmaster.Task.TodoTask;
import Taskmaster.Task.DeadlineTask;
import Taskmaster.Task.EventTask;
import Taskmaster.util.ParseFiles;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ParseFilesTest {
    private final ParseFiles FILEPARSER = new ParseFiles();

    @Test
    @DisplayName("Test Todotask creation")
    public void testTodoTask() {
        assertTrue(FILEPARSER.parseTask("T | 1 | me") instanceof TodoTask);
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testDeadlineTask() {
        assertTrue(FILEPARSER.parseTask("D | 0 | SpongeBob | 24/02/1998 1800") instanceof DeadlineTask);
    }

    @Test
    @DisplayName("Test EventTask creation")
    public void testEventTask() {
        assertTrue(FILEPARSER.parseTask("E | 0 | SpongeBob | 27/12/2000 0900") instanceof EventTask);
    }


}
