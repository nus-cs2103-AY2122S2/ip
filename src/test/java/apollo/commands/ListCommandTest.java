package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;
import apollo.tasks.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                + "\t1.[T][ ] todo task\n"
                + "\t2.[T][ ] todo task\n"
                + "\t3.[T][ ] todo task";
        assertEquals(expected, list.execute());
    }

    @Test
    void execute_noTasks_printEmptyList() throws ApolloOutOfBoundsException {
        Command list = new ListCommand();
        String expected = "You currently have no tasks. ";
        assertEquals(expected, list.execute());
    }
}
