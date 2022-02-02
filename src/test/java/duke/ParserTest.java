package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.ListAllTasksCommand;
import duke.command.MarkAsDoneCommand;
import duke.command.MarkAsUndoneCommand;
import duke.taskobjects.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest { // Just need to ensure it is returning the correct Command
    @Test
    public void testParse_list() {
        Parser parser = new Parser();
        try {
            Command resultCommand = parser.parseCommand("list");
            assertTrue(resultCommand instanceof ListAllTasksCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        //        Parser parser = new Parser();
//        try {
//            TaskList tsklist = new TaskList();
//            tsklist.add(new Todo("borrow book"));
//            tsklist.add(new Deadline("return book", "1999-03-03"));
//            tsklist.add(new Event("project meeting", "2022-01-24"));
//
//            parser.setTaskList(tsklist);
//
//
//        } catch (Exception e) {
//            fail(e.getMessage());
//        }
    }

    @Test
    public void testParse_exit() {
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        Command resultCommand;
        try {
            resultCommand = parser.parseCommand("exit");
            assertTrue(ExitCommand.isExitCommand(resultCommand));
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_mark() {
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        parser.setTaskList(taskList);
        taskList.add(new Todo("return book"));

        Command resultCommand;
        try {
            resultCommand = parser.parseCommand("mark 0");
            assertTrue(resultCommand instanceof MarkAsDoneCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_unmark() {
        Parser parser = new Parser();
        TaskList taskList = new TaskList();
        parser.setTaskList(taskList);
        taskList.add(new Todo("return book"));

        Command resultCommand;
        try {
            resultCommand = parser.parseCommand("unmark 0");
            assertTrue(resultCommand instanceof MarkAsUndoneCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_mark_incorrectInt() {
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        try {
            Command resultCommand = parser.parseCommand("mark 0");
            assertTrue(resultCommand instanceof ErrorCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Invalid number entered, index out of bounds", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_todo() {
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        try {
            Command resultCommand = parser.parseCommand("todo borrow book");
            assertTrue(resultCommand instanceof AddTodoCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_event() {
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        try {
            Command resultCommand = parser.parseCommand("event project meeting /at 2022-01-24");
            assertTrue(resultCommand instanceof AddEventCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_event_incorrectDate() { // No date or wrong /at
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        try {
            Command resultCommand = parser.parseCommand("event project meeting /not a date");
            assertTrue(resultCommand instanceof ErrorCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Please enter /by followed by a date in this format YYYY-MM-DD", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_deadline() {
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        try {
            Command resultCommand = parser.parseCommand("deadline project meeting /at 2022-01-24");
            assertTrue(resultCommand instanceof AddDeadlineCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_deadline_incorrectDate() {
        Parser parser = new Parser();
        parser.setTaskList(new TaskList());

        try {
            Command resultCommand = parser.parseCommand("deadline project meeting /not a date");
            assertTrue(resultCommand instanceof ErrorCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Please enter /by followed by a date in this format YYYY-MM-DD", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_delete() {
        Parser parser = new Parser();

    }

    @Test
    public void testParse_delete_incorrectInt() { // No int or wrong integer input

    }

    @Test
    public void testParse_find() {

    }

    @Test
    public void testParse_find_incorrectInt() {

    }

    @Test
    public void testParse_incorrectCommand() { // Testing ` and unknown command

    }
}
