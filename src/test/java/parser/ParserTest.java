package parser;

import ann.commands.Command;
import ann.data.TaskList;
import ann.data.tasks.Task;
import ann.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testParseDeadlineSuccess() {
        Command command = Parser.parse("add deadline finish IP /by 2022-01-27 18:00");
        command.setTaskList(new TaskList());
        command.executeCommand();
        assertEquals("Alright! I've added this task:\n[D][ ] finish IP (by 27 Jan 2022 06:00 PM)\nNow there is 1 task in your list.", command.getMessage());
    }

    @Test
    public void testParseEventFailure1() {
        Command command = Parser.parse("add event meeting /at   ");
        command.setTaskList(new TaskList());
        command.executeCommand();
        assertEquals("Oops! Please add a date and time for the event!", command.getMessage());
    }

    @Test
    public void testParseEventFailure2() {
        Command command = Parser.parse("add event meeting /at 12-03-2022 17:00");
        command.setTaskList(new TaskList());
        command.executeCommand();
        assertEquals("Oops! Please use the following format:\nadd event [content] /at yyyy-MM-dd HH:mm", command.getMessage());
    }

    @Test
    public void testParseMarkSuccess() {
        Command command = Parser.parse("mark 1");
        TaskList tl = new TaskList();
        tl.addTask(new Task("finish IP"));
        command.setTaskList(tl);
        command.executeCommand();
        assertEquals("Alright! I've marked this task as done:\n[T][X] finish IP", command.getMessage());
    }

    @Test
    public void testParseUnmarkFailure() {
        Command command = Parser.parse("unmark 2");
        TaskList tl = new TaskList();
        tl.addTask(new Task("finish IP", true));
        command.setTaskList(tl);
        command.executeCommand();
        assertEquals("Oops! You only have 1 task in your list!", command.getMessage());
    }

    @Test
    public void testParseDeleteFailure() {
        Command command = Parser.parse("delete");
        command.executeCommand();
        assertEquals("Oops! Please use the following format:\ndelete [task number]", command.getMessage());
    }

    @Test
    public void testParseIncorrectCommand() {
        Command command = Parser.parse("blah");
        command.executeCommand();
        assertEquals("Oops! Please enter a valid command!", command.getMessage());
    }
}
