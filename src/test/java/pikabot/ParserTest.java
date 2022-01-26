package pikabot;

import pikabot.exception.TodoException;
import pikabot.task.Deadline;
import pikabot.task.Task;
import pikabot.task.Todo;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void todo_withNoDescription_exceptionThrown() {
        try {
            String[] strArr = {"todo"};
            Todo currTodo = Parser.parseTodo(strArr);
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.",
                    e.getMessage());
        }
    }

    @Test
    public void deadline_withInvalidDateFormat_exceptionThrown() {
        try {
            String[] strArr = {"deadline", "finish hw /by 09-08-2022"};
            Deadline currDeadline = Parser.parseDeadline(strArr);
            fail();
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! Invalid deadline! Deadline " +
                    "has to be a valid date in numerical format YYYY-MM-DD", e.getMessage());
        }
    }

}
