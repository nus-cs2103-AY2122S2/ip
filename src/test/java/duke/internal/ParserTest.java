package duke.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.StoreDeadlineCommand;
import duke.commands.StoreEventCommand;
import duke.commands.StoreTodoCommand;




public class ParserTest {
    @Test
    public void handleCommandFeedback_valid_success() {
        Parser parser = new Parser();
        parser.addCommand(new DeleteCommand());
        parser.addCommand(new ExitCommand());
        parser.addCommand(new FindCommand());
        parser.addCommand(new ListCommand());
        parser.addCommand(new MarkCommand(true));
        parser.addCommand(new MarkCommand(false));
        parser.addCommand(new StoreDeadlineCommand());
        parser.addCommand(new StoreEventCommand());
        parser.addCommand(new StoreTodoCommand());

        try {
            //test case 1
            assertTrue(parser.parse("todo 1") instanceof StoreTodoCommand);

            //test case 2
            assertTrue(parser.parse("deadline 1") instanceof StoreDeadlineCommand);

            //test case 3
            assertTrue(parser.parse("event 1") instanceof StoreEventCommand);

            //test case 4
            assertTrue(parser.parse("list") instanceof ListCommand);

            //test case 5
            assertTrue(parser.parse("delete 1") instanceof DeleteCommand);

            //test case 6
            assertTrue(parser.parse("bye 1") instanceof ExitCommand);

            //test case 7
            assertTrue(parser.parse("mark 1") instanceof MarkCommand);

            //test case 8
            assertTrue(parser.parse("unmark 1") instanceof MarkCommand);

            //test case 9
            assertTrue(parser.parse("find 2") instanceof FindCommand);
        } catch (Exception e) {
            System.out.println("Exception hit! Test failed");
        }
    }

    @Test
    public void handleCommandFeedback_unrecognizedCommand_error() {
        Parser parser = new Parser();

        try {
            parser.parse("abcd");
            System.out.println("Exception missed! Test failed");
        } catch (Exception e) {
            assertEquals("☹!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void handleCommandFeedback_invalidDelete_error() {
        Parser parser = new Parser();
        parser.addCommand(new DeleteCommand());

        try {
            parser.parse("delete a");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "☹!!! Invalid input! Please enter the number of the task you want to delete.");
        }
    }

    @Test
    public void handleCommandFeedback_invalidMark_error() {
        Parser parser = new Parser();
        parser.addCommand(new MarkCommand(true));

        try {
            parser.parse("mark b");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "☹!!! Invalid input! Please enter the number "
                    + "of the task you want to mark/unmark.");
        }
    }

    @Test
    public void handleCommandFeedback_invalidUnmark_error() {
        Parser parser = new Parser();
        parser.addCommand(new MarkCommand(false));

        try {
            parser.parse("unmark b");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "☹!!! Invalid input! Please enter the number"
                    + " of the task you want to mark/unmark.");
        }
    }

    @Test
    public void handleCommandFeedback_invalidFind_error() {
        Parser parser = new Parser();
        parser.addCommand(new FindCommand());

        try {
            parser.parse("find");
        } catch (Exception e) {
            assertEquals(e.getMessage(), "☹!!! Invalid input! Please specify a description for the tasks to search!");
        }
    }

}
