package duke.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeadlineCommandTest {

    private TaskList<Task> tasks = new TaskList<>();
    private Ui ui = new Ui();
    private Storage storage = new Storage();

    @Test
    public void instantializationTest() {
        DeadlineCommand dlc = new DeadlineCommand("CS2105 Lab 2", "2022-02-28");
        dlc.execute(tasks, ui, storage);
        assertTrue(tasks.get(tasks.size()) instanceof Deadline);
    }
}
