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

public class ListCommandTest {
    private ListCommand cmd;

    @BeforeEach
    void setUp() {
        cmd = new ListCommand();
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
    void execute() {
        TaskList tasks = new TaskList();
        tasks.add(new ToDo("test"));
        System.out.println(tasks.size());
        assertNull(cmd.getList());
        cmd.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals(1, cmd.getList().size());



    }
}
