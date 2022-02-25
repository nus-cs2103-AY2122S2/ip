package duke.commands;

import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineCommandTest {

    TaskList<Task> tasks = new TaskList<>();
    Ui ui = new Ui();
    Storage storage = new Storage();

    @Test
    public void instantializationTest() {
        DeadlineCommand dlc =  new DeadlineCommand("CS2105 Lab 2", "2022-02-28");
        dlc.execute(tasks, ui, storage);
        assertTrue(tasks.get(tasks.size()) instanceof Deadline);
    }
}
