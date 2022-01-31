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



public class MarkCommandTest {
    private MarkCommand cmd;

    @BeforeEach
    void setUp() {
        cmd = new MarkCommand(3);
    }

    @Test
    void getListBeforeExecute() {
        assertNull(cmd.getList());
    }

    @Test
    void doesNotEnd() {
        assertFalse(cmd.endsProgram());
    }

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
