package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.TaskList;
import apollo.tasks.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindCommandTest {

    @BeforeEach
    void setUp() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Finding nemo and friends"));
        taskList.addTask(new Todo("grading Books"));
        taskList.addTask(new Todo("Friendship is magic"));
        Command.setTaskList(taskList);
    }

    @AfterEach
    void tearDown() {
        Command.setTaskList(null);
    }

    @Test
    void execute_twoMatches_foundTwo() throws ApolloOutOfBoundsException {
        Command find = new FindCommand("friEnd");
        String expected = "Here are your related tasks. \n"
                + "1.[T][ ] Finding nemo and friends\n"
                + "2.[T][ ] Friendship is magic";
        assertEquals(expected, find.execute());
    }

    @Test
    void execute_noMatches_foundNothing() throws ApolloOutOfBoundsException {
        Command find = new FindCommand("noMatch");
        String expected = "I could not find any related tasks. ";
        assertEquals(expected, find.execute());
    }
}