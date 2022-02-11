package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListAllTasksCommand;
import duke.command.MarkAsDoneCommand;
import duke.command.MarkAsUndoneCommand;
import duke.command.PostponeTaskCommand;
import duke.taskobjects.Deadline;
import duke.taskobjects.Todo;

public class ParserTest { // Just need to ensure it is returning the correct Command
    @Test
    public void testParse_list() {
        try {
            Parser parser = new Parser();
            Command resultCommand = parser.parseCommand("list");
            assertInstanceOf(ListAllTasksCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_exit() {
        try {
            Parser parser = new Parser();
            parser.setTaskList(new TaskList());

            Command resultCommand = parser.parseCommand("exit");
            assertInstanceOf(ExitCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_mark() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Todo("return book"));

            Command resultCommand = parser.parseCommand("mark 1");
            assertInstanceOf(MarkAsDoneCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_unmark() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Todo("return book"));

            Command resultCommand = parser.parseCommand("unmark 1");
            assertInstanceOf(MarkAsUndoneCommand.class, resultCommand);
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
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Invalid number entered, index out of bounds", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_todo() {
        try {
            Parser parser = new Parser();
            parser.setTaskList(new TaskList());

            Command resultCommand = parser.parseCommand("todo borrow book");
            assertInstanceOf(AddTodoCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_event() {
        try {
            Parser parser = new Parser();
            parser.setTaskList(new TaskList());

            Command resultCommand = parser.parseCommand("event project meeting /at 2022-01-24");
            assertInstanceOf(AddEventCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_event_incorrectDate() { // No date or wrong /at
        try {
            Parser parser = new Parser();
            parser.setTaskList(new TaskList());

            Command resultCommand = parser.parseCommand("event project meeting /not a date");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Please enter /by followed by a date in this format YYYY-MM-DD",
                    errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_deadline() {
        try {
            Parser parser = new Parser();
            parser.setTaskList(new TaskList());

            Command resultCommand = parser.parseCommand("deadline project meeting /by 2022-01-24");
            assertInstanceOf(AddDeadlineCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_deadline_incorrectDate() {
        try {
            Parser parser = new Parser();
            parser.setTaskList(new TaskList());

            Command resultCommand = parser.parseCommand("deadline project meeting /not a date");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Please enter /by followed by a date in this format YYYY-MM-DD",
                    errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_delete() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            taskList.add(new Todo("test"));
            parser.setTaskList(taskList);

            Command resultCommand = parser.parseCommand("delete 1");
            assertInstanceOf(DeleteCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_delete_incorrectInt() { // No int or wrong integer input
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);

            Command resultCommand = parser.parseCommand("delete 1");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Invalid number entered, index out of bounds", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_find() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);

            Command resultCommand = parser.parseCommand("find hello");
            assertInstanceOf(FindCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_incorrectCommand() { // Testing ` and unknown command
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);

            // Testing for ` detection
            Command resultCommand = parser.parseCommand("test `");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("\"`\" character is not allowed", errorCommand.runCommand().toString());

            // Testing for unknown command
            Command resultCommand1 = parser.parseCommand("unknown command");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand1 = (ErrorCommand) resultCommand1;
            assertEquals("Unknown command: unknown", errorCommand1.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_postpone() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Deadline("Test", LocalDate.parse("2000-03-01")));

            Command resultCommand = parser.parseCommand("postpone 1 2020-03-01");
            assertInstanceOf(PostponeTaskCommand.class, resultCommand);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_postpone_taskWithNoDate() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Todo("Test"));

            Command resultCommand = parser.parseCommand("postpone 1 2020-03-01");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("The selected task does not have a date", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_postpone_notEnoughArguments() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Deadline("Test", LocalDate.parse("2000-03-01")));

            Command resultCommand = parser.parseCommand("postpone");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Invalid arguments entered", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_postpone_incorrectDate() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Deadline("Test", LocalDate.parse("2000-03-01")));

            Command resultCommand = parser.parseCommand("postpone 1 test");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Please enter date in the format YYYY-MM-DD", errorCommand.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testParse_postpone_incorrectInt() {
        try {
            Parser parser = new Parser();
            TaskList taskList = new TaskList();
            parser.setTaskList(taskList);
            taskList.add(new Deadline("Test", LocalDate.parse("2000-03-01")));

            Command resultCommand = parser.parseCommand("postpone 10 2020-03-01");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand = (ErrorCommand) resultCommand;
            assertEquals("Invalid number entered, index out of bounds", errorCommand.runCommand().toString());

            Command resultCommand2 = parser.parseCommand("postpone test 2020-03-01");
            assertInstanceOf(ErrorCommand.class, resultCommand);
            ErrorCommand errorCommand2 = (ErrorCommand) resultCommand2;
            assertEquals("That's not a number", errorCommand2.runCommand().toString());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
