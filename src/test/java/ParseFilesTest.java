import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import taskmaster.task.DeadlineTask;
import taskmaster.task.EventTask;
import taskmaster.task.TodoTask;
import taskmaster.util.ParseFiles;

public class ParseFilesTest {
    private final ParseFiles FILE_PARSER = new ParseFiles();

    @Test
    @DisplayName("Test Todo task creation")
    public void testTodoTask() {
        assertTrue(FILE_PARSER.parseTask("T | 1 | me") instanceof TodoTask);
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testDeadlineTask() {
        assertTrue(FILE_PARSER.parseTask("D | 0 | SpongeBob | 24/02/1998 1800") instanceof DeadlineTask);
    }

    @Test
    @DisplayName("Test EventTask creation")
    public void testEventTask() {
        assertTrue(FILE_PARSER.parseTask("E | 0 | SpongeBob | 27/12/2000 0900") instanceof EventTask);
    }


}
