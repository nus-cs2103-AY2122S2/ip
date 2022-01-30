package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.TaskList;
import apollo.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MarkCommandTest {

    @BeforeEach
    void setUp() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("todo1"));
        taskList.addTask(new Todo("todo2"));
        taskList.addTask(new Todo("todo3"));
        Command.setTaskList(taskList);
    }

    @AfterEach
    void tearDown() {
        Command.setTaskList(null);
    }

    @Test
    void execute_markTodo3() throws ApolloOutOfBoundsException {
        Command mark3 = new MarkCommand(2, true);
        String expected = "I have marked the following task as done\n"
                + "\t[T][X] todo3";
        assertEquals(expected, mark3.execute());
    }

    @Test
    void execute_outOfBoundsIndex_exceptionThrown() {
        Exception exception = assertThrows(ApolloOutOfBoundsException.class, () ->{
            Command error = new MarkCommand(4, true);
            error.execute();
        });
        String errorExpected = "Index is not on the list. ";
        assertEquals(errorExpected, exception.getMessage());
    }
}
