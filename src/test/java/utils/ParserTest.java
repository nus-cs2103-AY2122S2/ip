package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteAllCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ShowAllTasksOnSameDateCommand;
import duke.command.UnmarkCommand;
import duke.task.Todo;
import duke.utils.CortanaException;
import duke.utils.Parser;

public class ParserTest {
    @Test
    @DisplayName("Should parse to ExitCommand")
    public void parsedToExitCommand() {
        try {
            ExitCommand exitCommand = new ExitCommand();
            Command parsedCommand = Parser.parse("bye");
            assertEquals(exitCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to ListCommand")
    public void parsedToListCommand() {
        try {
            ListCommand listCommand = new ListCommand();
            Command parsedCommand = Parser.parse("list");
            assertEquals(listCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to MarkCommand")
    public void parsedToMarkCommand() {
        try {
            MarkCommand markCommand = new MarkCommand(0);
            Command parsedCommand = Parser.parse("mark 1");
            assertEquals(markCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to UnmarkCommand")
    public void parsedToUnmarkCommand() {
        try {
            UnmarkCommand unmarkCommand = new UnmarkCommand(0);
            Command parsedCommand = Parser.parse("unmark 1");
            assertEquals(unmarkCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to DeleteCommand")
    public void parsedToDeleteCommand() {
        try {
            DeleteCommand deleteCommand = new DeleteCommand(0);
            Command parsedCommand = Parser.parse("delete 1");
            assertEquals(deleteCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to DeleteAllCommand")
    public void parsedToDeleteAllCommand() {
        try {
            DeleteAllCommand deleteAllCommand = new DeleteAllCommand();
            Command parsedCommand = Parser.parse("delete all");
            assertEquals(deleteAllCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to ShowAllTasksOnSameDateCommand")
    public void parsedToShowAllTasksOnSameDateCommand() {
        try {
            LocalDateTime localDateTime = LocalDateTime.parse("2022-01-24 1800",
                    DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
            ShowAllTasksOnSameDateCommand showAllTasksOnSameDateCommand = new ShowAllTasksOnSameDateCommand(
                    localDateTime, "2022-01-24 1800");
            Command parsedCommand = Parser.parse("show all 2022-01-24 1800");
            assertEquals(showAllTasksOnSameDateCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should parse to FindCommand")
    public void parsedToFindCommand() {
        try {
            String keyword = "book";
            FindCommand findCommand = new FindCommand(keyword);
            Command parsedCommand = Parser.parse("find book");
            assertEquals(findCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    @DisplayName("Should ignore and throw exception")
    public void invalidInput() {
        try {
            Parser.parse("something invalid");
            fail();
        } catch (CortanaException e) {
            assertEquals("I don't know what that means :(", e.getMessage());
        }
    }

    @Test
    @DisplayName("Should parse to AddCommand")
    public void parsedToAddCommand() {
        try {
            Todo dummyTodo = new Todo("watch lecture");
            AddCommand markCommand = new AddCommand(dummyTodo);
            Command parsedCommand = Parser.parse("todo watch lecture");
            assertEquals(markCommand, parsedCommand);
        } catch (CortanaException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
