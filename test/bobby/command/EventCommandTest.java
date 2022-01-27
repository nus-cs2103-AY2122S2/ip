package bobby.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventCommandTest {

    @Test
    void isValidDate() {
        EventCommand eventCommand = new EventCommand("event");
        assertTrue(eventCommand.isValidDate("12-01-1998"));
        assertTrue(eventCommand.isValidDate("01-12-1998"));
        assertFalse(eventCommand.isValidDate("00-01-1998"));
        assertFalse(eventCommand.isValidDate("01-01-19988"));
        assertFalse(eventCommand.isValidDate("01-01-abc"));
        assertFalse(eventCommand.isValidDate("01-32-1998"));
    }
}