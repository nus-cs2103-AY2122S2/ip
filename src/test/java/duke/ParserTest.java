package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseDateTime_validDateTime_success() {
        assertDoesNotThrow(() -> Parser.parseDateTime("2022-4-22"));
        assertDoesNotThrow(() -> Parser.parseDateTime("2022-11-5"));
        assertDoesNotThrow(() -> Parser.parseDateTime("2022-11-22"));
        assertDoesNotThrow(() -> Parser.parseDateTime("2022-11-22 1430"));
    }

    @Test
    public void parseDateTime_invalidDateTime_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("6 June 2022"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5/11/2022"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("2022-11-22 2500"));
    }

    @Test
    public void parseDuration_validDuration_success() {
        assertDoesNotThrow(() -> Parser.parseDuration("24h"));
        assertDoesNotThrow(() -> Parser.parseDuration("5h"));
        assertDoesNotThrow(() -> Parser.parseDuration("5m"));
        assertDoesNotThrow(() -> Parser.parseDuration("60m"));
        assertDoesNotThrow(() -> Parser.parseDuration("5h5m"));
    }

    @Test
    public void parseDuration_invalidDuration_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5s"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5d"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5hours"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5h5"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5h5m5s"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5minutes"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5m5"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("5seconds"));
    }
}
