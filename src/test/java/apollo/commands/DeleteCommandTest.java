package apollo.commands;

import static apollo.messages.Messages.OUT_OF_BOUNDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.TaskList;
import apollo.tasks.Todo;

public class DeleteCommandTest {

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
    void execute_deleteTodo2_returnTodo2() throws ApolloOutOfBoundsException {
        Command delete = new DeleteCommand(1);
        String expected = "Alright, I've removed this task. \n"
                + "  [T][ ] todo2\n"
                + "There's a total of 2 tasks now. ";
        assertEquals(expected, delete.execute());
    }

    @Test
    void execute_outOfBoundsIndex_exceptionThrown() {
        Exception exception = assertThrows(ApolloOutOfBoundsException.class, () -> {
            Command error = new DeleteCommand(4);
            error.execute();
        });
        assertEquals(OUT_OF_BOUNDS, exception.getMessage());
    }
}
