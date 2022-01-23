package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Represents tests for the <code>Parser</code> class
 */
public class ParserTest {

    /**
     * Tests the parsing of Strings from a backup file for <code>ToDo</code> objects
     */
    @Test
    public void parseFileToDo() {
        try {
            assertTrue(Parser.parseFile("ToDo homework done") instanceof ToDo);
            assertTrue(Parser.parseFile("ToDo dishes undone") instanceof ToDo);
            assertTrue(Parser.parseFile("ToDo homework blablabla") instanceof ToDo);
            assertTrue(Parser.parseFile("ToDo") instanceof ToDo);
        } catch (DukeException e) {
            assertEquals("Backup file cannot be read.", e.toString());
        }
    }

    /**
     * Tests the parsing of Strings from a backup file for <code>Deadline</code> objects
     */
    @Test
    public void parseFileDeadline() {
        try {
            assertTrue(Parser.parseFile("Deadline homework /by 9pm done") instanceof Deadline);
            assertTrue(Parser.parseFile("Deadline assignment /by 13 Jan undone") instanceof Deadline);
            assertTrue(Parser.parseFile("Deadline homework /by 9pm testing") instanceof Deadline);
            assertTrue(Parser.parseFile("Deadline") instanceof Deadline);
        } catch (DukeException e) {
            assertEquals("Backup file cannot be read.", e.toString());
        }
    }

    /**
     * Tests the parsing of Strings from a backup file for <code>Event</code> objects
     */
    @Test
    public void parseFileEvent() {
        try {
            assertTrue(Parser.parseFile("Event drinks /at 9pm done") instanceof Event);
            assertTrue(Parser.parseFile("Event party /at 13 Jan undone") instanceof Event);
            assertTrue(Parser.parseFile("Event homework /at 9pm testing") instanceof Event);
            assertTrue(Parser.parseFile("Event /at") instanceof Event);
        } catch (DukeException e) {
            assertEquals("Backup file cannot be read.", e.toString());
        }
    }

    /**
     * Tests the parsing of Tasks to be saved into a backup file
     */
    @Test
    public void parseSavedTasks() {
        assertEquals((Parser.parseSavedEventTask(new Event("sleep", "9pm"))),
                "Event sleep /at 9pm");
        assertEquals((Parser.parseSavedDeadlineTask(new Deadline("sleep", "9pm"))),
                "Deadline sleep /by 9pm");
        assertEquals((Parser.parseSavedToDoTask(new ToDo("sleep"))),
                "ToDo sleep");
    }

}
