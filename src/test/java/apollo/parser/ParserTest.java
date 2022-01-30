package apollo.parser;

import apollo.commands.*;
import apollo.exceptions.ApolloIllegalArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private static final Parser parser = new Parser();
    private final String dateTime = "29-01-2022 00:00";

    @Test
    void parseCommand_todoTest_todoInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("toDO Test") instanceof AddCommand);
    }

    @Test
    void parseCommand_todoMissingArguments_throwException() {
        Exception exception = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("todo");
        });
        String expected = "Insufficient Arguments. ";
        assertEquals(exception.getMessage(), expected);
    }

    @Test
    void parseCommand_deadlineTest_deadlineInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("deaDLIne Test /by " + dateTime) instanceof AddCommand);
    }

    @Test
    void parseCommand_deadlineMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("deadline   ");
        });
        Exception exception2 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("deadline   test");
        });
        String expected1 = "Insufficient Arguments. ";
        String expected2 = "Please add date and time in this format: dd-MM-yyyy HH:mm";
        assertEquals(exception1.getMessage(), expected1);
        assertEquals(exception2.getMessage(), expected2);
    }

    @Test
    void parseCommand_eventTest_eventInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("evenT Test /at " + dateTime) instanceof AddCommand);
    }

    @Test
    void parseCommand_eventMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("event   ");
        });
        Exception exception2 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("event   test /bt");
        });
        String expected1 = "Insufficient Arguments. ";
        String expected2 = "Please add date and time in this format: dd-MM-yyyy HH:mm";
        assertEquals(exception1.getMessage(), expected1);
        assertEquals(exception2.getMessage(), expected2);
    }

    @Test
    void parseCommand_markTest_markInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("MARk 1") instanceof MarkCommand);
        assertTrue(parser.parseCommand("UNmARK 1") instanceof MarkCommand);
    }

    @Test
    void parseCommand_markMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("Mark  ");
        });
        Exception exception2 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("unmark  ");
        });
        String expected = "Insufficient Arguments. ";
        assertEquals(exception1.getMessage(), expected);
        assertEquals(exception2.getMessage(), expected);
    }

    @Test
    void parseCommand_deleteTest_deleteInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("DeleTe 1") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_deleteMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> {
            parser.parseCommand("   delete    ");
        });
        String expected = "Insufficient Arguments. ";
        assertEquals(exception1.getMessage(), expected);
    }

    @Test
    void parseCommand_listTest_listInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("lISt") instanceof ListCommand);
    }

    @Test
    void parseCommand_exitTest_exitInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("eXit") instanceof ExitCommand);
    }

    @Test
    void parseCommand_invalidTest_invalidInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("anyOtherString") instanceof InvalidCommand);
        assertTrue(parser.parseCommand("any Other String") instanceof InvalidCommand);
    }
}
