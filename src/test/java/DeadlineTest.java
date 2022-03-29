import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Storage storage = new Storage("");
    TaskList taskList = new TaskList();
    Ui ui = new Ui();
    Parser parserTest = new Parser(storage, taskList, ui);

    @Test
    public void dateConversionTest() {
        parserTest.parse("deadline Repair the windows /by 08/08/2020");
        String expected = ("[D][ ] Repair the windows (by: 8 AUGUST 2020)");
        assertEquals(expected,taskList.getTaskList().get(0).toString());
    }
}
