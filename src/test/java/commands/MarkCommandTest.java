package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.DukeException;
import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

/**
 * Class to test mark command
 */
public class MarkCommandTest {
    private MarkCommand cmd;

    /**
     * Method executed to set-up before each test
     * ensures command contains a new mark command
     */
    @BeforeEach
    void setUp() {
        cmd = new MarkCommand(3);
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
     * Tests command marks a task from the list given a valid index
     */
    @Test
    void marks() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test2"));
        tasks.add(new ToDo("test3"));
        try {
            cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
            assertEquals("1", cmd.getList().get(2).getMark());
        } catch (DukeException e) {
            e.getMessage();
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
