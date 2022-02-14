import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import saitama.commands.ExitCommand;
import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

class ExitCommandTest {

    private String filePath = "data/ExitTest.txt";
    private ExitCommand command = new ExitCommand();
    private Ui ui = new Ui();
    private TaskList taskList = new TaskList(new ArrayList<>());
    private Storage storage = new Storage(filePath);

    @Test
    void execute() {
        assertEquals(ui.showExit(), command.execute(taskList, ui, storage));
    }

    @Test
    void isExit() {
        assertEquals(true, command.isExit());
    }
}
