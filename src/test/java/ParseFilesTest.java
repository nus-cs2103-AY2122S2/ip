import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.TodoTask;
import taskmaster.util.ParseFiles;


public class ParseFilesTest {
    private ParseFiles fileParser = new ParseFiles();

    @Test
    @DisplayName("Test Todo task creation")
    public void testTodoTask() {
        assertTrue(fileParser.parseTask("T | 1 | me") instanceof TodoTask);
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testDeadlineTask() {
        assertTrue(fileParser.parseTask("D | 0 | SpongeBob | 24/02/1998 1800") instanceof DeadlineTask);
    }

    @Test
    @DisplayName("Test EventTask creation")
    public void testEventTask() {
        assertTrue(fileParser.parseTask("E | 0 | SpongeBob | 27/12/2000 0900") instanceof EventTask);
    }


}
