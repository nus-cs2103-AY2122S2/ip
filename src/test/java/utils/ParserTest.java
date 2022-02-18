package utils;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import echo.command.ByeCommand;
import echo.command.DeadlineCommand;
import echo.command.DeleteCommand;
import echo.command.EventCommand;
import echo.command.FindCommand;
import echo.command.HelpCommand;
import echo.command.ListCommand;
import echo.command.MarkCommand;
import echo.command.TodoCommand;
import echo.command.UnmarkCommand;
import echo.parser.Parser;
import echo.utils.EchoException;

public class ParserTest {

    @Test
    @DisplayName("Should parse to ListCommand")
    public void parse_validListCommand_success() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to TodoCommand")
    public void parse_validTodoCommand_success() {
        try {
            assertTrue(Parser.parse("todo borrow book") instanceof TodoCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to DeadlineCommand")
    public void parse_validDeadlineCommand_success() {
        try {
            assertTrue(Parser.parse("deadline sell book /by 2022-01-01 1800") instanceof DeadlineCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to EventCommand")
    public void parse_validEventCommand_success() {
        try {
            assertTrue(Parser.parse("event get rich /at 2022-04-20 0400") instanceof EventCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to MarkCommand")
    public void parse_validMarkCommand_success() {
        try {
            assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to UnmarkCommand")
    public void parse_validUnmarkCommand_success() {
        try {
            assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to DeleteCommand")
    public void parse_validDeleteCommand_success() {
        try {
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to HelpCommand")
    public void parse_validHelpCommand_success() {
        try {
            assertTrue(Parser.parse("hi") instanceof HelpCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to ByeCommand")
    public void parse_validByeCommand_success() {
        try {
            assertTrue(Parser.parse("bye") instanceof ByeCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to FindCommand")
    public void parse_validFindCommand_success() {
        try {
            assertTrue(Parser.parse("find book") instanceof FindCommand);
        } catch (EchoException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
