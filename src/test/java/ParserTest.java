import exceptions.DukeException;
import user.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    public void addTask_invalidDeadline_exceptionThrown() {
        try {
            parser.addTask("deadline  /by ");
            fail();
        } catch (DukeException err) {
            String correctString = "Format for deadlines: 'deadline [some task] /by [dd/mm/yyyy-hh:mm]'";
            assertEquals(correctString, err.getMessage());
        }
    }

    @Test
    public void handleDeleteTask_invalidTaskNum_exceptionThrown() {
        try {
            parser.handleDeleteTask("delete five", 4);
            fail();
        } catch (DukeException err) {
            String correctString = "Not a valid task number!";
            assertEquals(correctString, err.getMessage());
        }
    }

    @Test
    public void handleDeleteTask_NoSuchTaskNum_exceptionThrown() {
        try {
            parser.handleDeleteTask("delete 5", 4);
            fail();
        } catch (DukeException err) {
            String correctString = "Task 5 does not exist!";
            assertEquals(correctString, err.getMessage());
        }
    }
}
