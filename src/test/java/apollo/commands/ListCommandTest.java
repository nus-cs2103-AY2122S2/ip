package apollo.commands;

import static apollo.messages.Messages.EMPTY_TASKLIST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;

public class ListCommandTest {

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
    void execute_threeTasks_printIndexedList() throws ApolloOutOfBoundsException {
        Command addTodo = new AddCommand("todo task", null, Task.Type.TODO);
        addTodo.execute();
        addTodo.execute();
        addTodo.execute();
        Command list = new ListCommand();
        String expected = "These are your current tasks. \n"
                + "1.[T][ ] todo task\n"
                + "2.[T][ ] todo task\n"
                + "3.[T][ ] todo task";
        assertEquals(expected, list.execute());
    }

    @Test
    void execute_noTasks_printEmptyList() throws ApolloOutOfBoundsException {
        Command list = new ListCommand();
        assertEquals(EMPTY_TASKLIST, list.execute());
    }
}
