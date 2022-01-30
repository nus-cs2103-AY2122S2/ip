package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {

    private final LocalDateTime dateTime = LocalDateTime.of(2022, 1, 20, 0, 0);

    @BeforeEach
    void setUp() {
        TaskList taskList = new TaskList();
        Command.setTaskList(taskList);
    }

    @AfterEach
    void tearDown() {
        Command.setTaskList(null);
    }

    @Test
    @Order(3)
    void execute_todo_todoInstance() throws ApolloOutOfBoundsException {
        Command todo = new AddCommand("task", null, Task.Type.TODO);
        String expected = "I've added this task:\n"
                + "  [T][ ] task\n"
                + "There's a total of 1 tasks now. ";
        assertEquals(expected, todo.execute());
    }

    @Test
    @Order(2)
    void execute_deadline_deadlineInstance() throws ApolloOutOfBoundsException {
        Command deadline = new AddCommand("task", dateTime, Task.Type.DEADLINE);
        String expected = "I've added this task:\n"
                + "  [D][ ] task (by: 20-01-2022 00:00)\n"
                + "There's a total of 1 tasks now. ";
        assertEquals(expected, deadline.execute());
    }

    @Test
    @Order(1)
    void execute_event_eventInstance() throws ApolloOutOfBoundsException {
        Command event = new AddCommand("task", dateTime, Task.Type.EVENT);
        String expected = "I've added this task:\n"
                + "  [E][ ] task (at: 20-01-2022 00:00)\n"
                + "There's a total of 1 tasks now. ";
        assertEquals(expected, event.execute());
    }
}
