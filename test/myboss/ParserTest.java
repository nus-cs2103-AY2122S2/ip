package myboss;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ParserTest {

    @Test
    void testSplitUserCmd() {
        String input = "todo borrow book";
        String[] expected = {"todo", "borrow book"};
        assertArrayEquals(expected, Parser.splitUserCmd(input));
    }

    @Test
    void testGetTaskIndex() {
        String[] input = {"mark", "3"};
        int expected = 2;
        assertEquals(expected, Parser.getTaskIndex(input));
    }

    @Test
    void testGetRemainingUserCmd() {
        String[] input = {"todo", "borrow book"};
        String expected = "borrow book";
        assertEquals(expected, Parser.getRemainingUserCmd(input));
    }

    @Test
    void testGetRemainingUserCmd_ExitCase_EmptyString() {
        String[] input = {"bye"};
        String expected = "";
        assertEquals(expected, Parser.getRemainingUserCmd(input));
    }

    @Test
    void testParseDeadlineUserCmd() {
        String input = "return book /by 2023-10-01";
        String[] expected = {"return book", "2023-10-01"};
        assertArrayEquals(expected, Parser.parseDeadlineUserCmd(input));
    }

    @Test
    void testParseEventUserCmd() {
        String input = "return book /at 2023-10-01 2pm";
        String[] expected = {"return book", "2023-10-01 2pm"};
        assertArrayEquals(expected, Parser.parseEventUserCmd(input));
    }
}