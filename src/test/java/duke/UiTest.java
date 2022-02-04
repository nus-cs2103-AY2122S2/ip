package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UiTest class that tests the display of the GUI of Duke application
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-4
 */

public class UiTest {
    Ui ui = new Ui();

    /** Runs a test to check the starting message of Duke application */
    @Test
    public void validWelcomeMessage() {
        String listOfCommands = "todo <Description of Task>\n" +
                "deadline <Description of Task> /by <eg. 2020/May/19>\n" +
                "event <Description of Task> /at <eg. 2020/May/19>\n" +
                "list\n" +
                "mark <Task number>\n" +
                "unmark <Task number>\n" +
                "delete <Task number>\n" +
                "find <Task number>\n" +
                "bye\n";
        String expected = "Welcome to Duke, your personal Task assistant!\n" + listOfCommands;
        String output = ui.createWelcomeMessage();
        assertEquals(expected, output);
    }
}
