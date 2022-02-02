package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import duke.commands.UnMarkCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Class to test unmark command
 */
public class UnMarkCommandTest {
    private UnMarkCommand cmd;

    /**
     * Method executed to set-up before each test
     * ensures command contains a new unmark command
     */
    @BeforeEach
    void setUp() {
        cmd = new UnMarkCommand(3);
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
     * Tests command unmarks a task from the list given a valid index
     */
    @Test
    void unMarks() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        tasks.add(new ToDo("test2"));
        tasks.add(new ToDo("test3"));
        Task marked = tasks.get(2);
        marked.mark();
        tasks.set(2, marked);
        assertEquals("1", tasks.get(2).getMark());
        try {
            cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
            assertEquals("0", cmd.getList().get(2).getMark());
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
