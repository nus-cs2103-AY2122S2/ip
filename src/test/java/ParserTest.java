import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;

public class ParserTest {
    @Test
    public void processMessage_wrongFormatMessage_exceptionThrown() throws IOException {
        Parser parser = new Parser();
        Storage storage = new Storage("data/duke.txt");
        try {
            storage.load();
        } catch (DukeException e) {
            //do nothing
        }

        Boolean check = false;

        try {
            parser.processMessage("deadline do stuff /by 20222-01-01", new TaskList(),
                storage);
        } catch (DukeException d) {
            check = true;
        }

        assertTrue(check);
    }

    @Test
    public void processMessage_event_addToList() throws IOException {
        Parser parser = new Parser();
        Storage storage = new Storage("data/test.txt");
        String output = "";

        File file = new File("data/test.txt");

        if (file.exists()) {
            file.delete();
        }


        try {
            storage.load();
        } catch (DukeException e) {
            //do nothing
        }

        try {
            output = parser.processMessage("event do stuff /at 2022-01-01/12:00/18:00", new TaskList(),
                storage);
        } catch (DukeException e) {
            assertTrue(false); //test fails if exception is thrown
        }

        assertEquals("Alright then! I've added the task to your list:\n"
            + "\t[E][ ] do stuff (at: Jan 1 2022 from 12:00 PM to 06:00 PM)\n"
            + "You currently have 1 task(s) remaining in your list.", output);
    }
}
