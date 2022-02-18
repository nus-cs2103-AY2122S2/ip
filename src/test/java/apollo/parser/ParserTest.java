package apollo.parser;

import static apollo.messages.Messages.INSUFFICIENT_ARGUMENTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import apollo.commands.AddCommand;
import apollo.commands.DeleteCommand;
import apollo.commands.ExitCommand;
import apollo.commands.FindCommand;
import apollo.commands.InvalidCommand;
import apollo.commands.ListCommand;
import apollo.commands.MarkCommand;
import apollo.exceptions.ApolloIllegalArgumentException;

public class ParserTest {

    private static final Parser parser = new Parser();
    private final String dateTime = "29-01-2022 00:00";

    @Test
    void parseCommand_todoTest_todoInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("toDO Test") instanceof AddCommand);
    }

    @Test
    void parseCommand_todoMissingArguments_throwException() {
        Exception exception = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("todo"));
        assertEquals(INSUFFICIENT_ARGUMENTS, exception.getMessage());
    }

    @Test
    void parseCommand_deadlineTest_deadlineInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("deaDLIne Test /by " + dateTime) instanceof AddCommand);
    }

    @Test
    void parseCommand_deadlineMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("deadline   "));
        Exception exception2 = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("deadline   test"));
        String expected2 = "Please add date and time in this format: dd-MM-yyyy HH:mm";
        assertEquals(INSUFFICIENT_ARGUMENTS, exception1.getMessage());
        assertEquals(expected2, exception2.getMessage());
    }

    @Test
    void parseCommand_eventTest_eventInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("evenT Test /at " + dateTime) instanceof AddCommand);
    }

    @Test
    void parseCommand_eventMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("event   "));
        Exception exception2 = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("event   test /bt"));
        String expected2 = "Please add date and time in this format: dd-MM-yyyy HH:mm";
        assertEquals(INSUFFICIENT_ARGUMENTS, exception1.getMessage());
        assertEquals(expected2, exception2.getMessage());
    }

    @Test
    void parseCommand_markTest_markInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("MARk 1") instanceof MarkCommand);
        assertTrue(parser.parseCommand("UNmARK 1") instanceof MarkCommand);
    }

    @Test
    void parseCommand_markMissingArguments_throwException() {
        Exception exception1 = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("Mark  "));
        Exception exception2 = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("unmark  "));
        assertEquals(INSUFFICIENT_ARGUMENTS, exception1.getMessage());
        assertEquals(INSUFFICIENT_ARGUMENTS, exception2.getMessage());
    }

    @Test
    void parseCommand_deleteTest_deleteInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("DeleTe 1") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_deleteMissingArguments_throwException() {
        Exception exception = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("   delete    "));
        assertEquals(INSUFFICIENT_ARGUMENTS, exception.getMessage());
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

    @Test
    void parseCommand_findTest_findInstance() throws ApolloIllegalArgumentException {
        assertTrue(parser.parseCommand("find word") instanceof FindCommand);
        assertTrue(parser.parseCommand("find multiple words") instanceof FindCommand);
    }

    @Test
    void parseCommand_findMissing_throwException() {
        Exception exception = assertThrows(ApolloIllegalArgumentException.class, () -> parser
                .parseCommand("   find    "));
        assertEquals(INSUFFICIENT_ARGUMENTS, exception.getMessage());
    }
}
