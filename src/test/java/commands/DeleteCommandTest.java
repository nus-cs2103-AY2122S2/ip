package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import duke.commands.DeleteCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Class to test delete command
 */
public class DeleteCommandTest {
    private DeleteCommand cmd;

    /**
     * Method executed to set-up before each test
     * ensures command contains a new delete command
     */
    @BeforeEach
    void setUp() {
        cmd = new DeleteCommand(3);
    }

    /**
     * Tests command does not store task list across commands
     */
    @Test
    void getListBeforeExecute() {
        assertNull(cmd.getList());
    }

    /**
     * Tests command returns correct boolean value
     * for checking if program terminates
     */
    @Test
    void doesNotEnd() {
        assertFalse(cmd.endsProgram());
    }

    /**
     * Tests command deletes a task from the list given a valid index
     */
    @Test
    void deletes() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test2"));
        tasks.add(new ToDo("test3"));
        try {
            cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
            assertEquals(2, cmd.getList().size());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    /**
     * Tests command does not delete a task from a list given invalid index
     * tests that error message printed is the correct one
     */
    @Test
    void throwsOutOfRangeException() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test2"));

        try {
            cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
            fail();
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The value input is not in the list", e.getMessage());
        }
    }
}
