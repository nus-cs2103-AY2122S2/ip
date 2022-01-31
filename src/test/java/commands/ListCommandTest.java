package commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

/**
 * Class to test list command
 */
public class ListCommandTest {
    private ListCommand cmd;
    /**
     * Method executed to set-up before each test
     * ensures command contains a new list command
     */
    @BeforeEach
    void setUp() {
        cmd = new ListCommand();
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
     * Tests command correctly utilizes task list parsed
     */
    @Test
    void execute() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        System.out.println(tasks.size());
        assertNull(cmd.getList());
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals(1, cmd.getList().size());



    }
}
