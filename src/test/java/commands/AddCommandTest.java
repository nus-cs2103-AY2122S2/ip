package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import duke.commands.AddCommand;
import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Class to test Add command
 */
public class AddCommandTest {
    private AddCommand cmd;

    /**
     * Tests command does not store task list across commands
     */
    @Test
    void getListBeforeExecuteToDo() {
        cmd = new AddCommand("todo", "return book");
        assertNull(cmd.getList());
    }

    /**
     * Tests command returns correct boolean value
     * for checking if program terminates
     */
    @Test
    void doesNotEndToDo() {
        cmd = new AddCommand("todo", "return book");
        assertFalse(cmd.endsProgram());
    }

    /**
     * Tests command does not store task list across commands
     */
    @Test
    void getListBeforeExecuteEvent() {
        cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
        assertNull(cmd.getList());
    }

    /**
     * Tests command returns correct boolean value
     * for checking if program terminates
     */
    @Test
    void doesNotEndEvent() {
        cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
        assertFalse(cmd.endsProgram());
    }

    /**
     * Tests command does not store task list across commands
     */
    @Test
    void getListBeforeExecuteDeadline() {
        cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
        assertNull(cmd.getList());
    }

    /**
     * Tests command returns correct boolean value
     * for checking if program terminates
     */
    @Test
    void doesNotEndDeadline() {
        cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
        assertFalse(cmd.endsProgram());
    }

    /**
     * Tests command adds to task list based on user input
     */
    @Test
    void addsToList() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
        cmd = new AddCommand("todo", "return book");
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        tasks = cmd.getList();
        assertEquals(1, tasks.size());

        cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        tasks = cmd.getList();
        assertEquals(2, tasks.size());

        cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        tasks = cmd.getList();
        assertEquals(3, tasks.size());
    }

    /**
     * Tests command adds the correct task type into the list
     */
    @Test
    void addsCorrectTaskType() {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
        cmd = new AddCommand("todo", "return book");
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("T", cmd.getList().get(0).getType());
        cmd = new AddCommand("event", "return book /at 2/2/2022 1200");
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("E", cmd.getList().get(1).getType());
        cmd = new AddCommand("deadline", "return book /by 2/2/2022 1200");
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("D", cmd.getList().get(2).getType());
    }
}
