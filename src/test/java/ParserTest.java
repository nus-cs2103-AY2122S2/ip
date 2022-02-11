import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Todo;

public class ParserTest {

    @Test
    public void parse_basic_list() {
        TaskList taskList = new TaskList();
        assertEquals("You don't have tasks listed.",
                new Parser(taskList, new Storage("")).parse("list"));

        taskList.readFromFile(new Todo("read book"));
        assertEquals("Here are the tasks in your list:\n"
                        + "1.[T][ ] read book",
                new Parser(taskList, new Storage("")).parse("list"));
    }

    @Test
    public void parse_basic_todo() {
        TaskList taskList = new TaskList();
        assertEquals("Got it. I've added this task:\n"
                        + "  [T][ ] read book\n"
                        + "Now you have 1 task in the list.",
                new Parser(taskList, new Storage("")).parse("todo read book"));

        assertEquals("Got it. I've added this task:\n"
                        + "  [T][ ] have lunch\n"
                        + "Now you have 2 tasks in the list.",
                new Parser(taskList, new Storage("")).parse("todo have lunch"));
    }

    @Test
    public void parse_basic_deadline() {
        TaskList taskList = new TaskList();
        assertEquals("Got it. I've added this task:\n"
                        + "  [D][ ] read book (by: 02 Jan 2022)\n"
                        + "Now you have 1 task in the list.",
                new Parser(taskList, new Storage("")).parse("deadline read book /by 2022-01-02"));

        assertEquals("Got it. I've added this task:\n"
                        + "  [D][ ] have lunch (by: 03 Jan 2022)\n"
                        + "Now you have 2 tasks in the list.",
                new Parser(taskList, new Storage("")).parse("deadline have lunch /by 2022-01-03"));
    }

    @Test
    public void parse_basic_event() {
        TaskList taskList = new TaskList();
        assertEquals("Got it. I've added this task:\n"
                        + "  [E][ ] read book (at: 2-4pm)\n"
                        + "Now you have 1 task in the list.",
                new Parser(taskList, new Storage("")).parse("event read book /at 2-4pm"));

        assertEquals("Got it. I've added this task:\n"
                        + "  [E][ ] have lunch (at: 4-6pm)\n"
                        + "Now you have 2 tasks in the list.",
                new Parser(taskList, new Storage("")).parse("event have lunch /at 4-6pm"));
    }

    @Test
    public void parse_basic_mark() {
        TaskList taskList = new TaskList();
        taskList.readFromFile(new Todo("read book"));
        assertEquals("Nice! I've marked this task as done:\n"
                        + "  [T][X] read book",
                new Parser(taskList, new Storage("")).parse("mark 1"));
    }

    @Test
    public void parse_basic_unmark() {
        TaskList taskList = new TaskList();
        taskList.readFromFile(new Todo("read book"));
        assertEquals("OK, I've marked this task as not done yet:\n"
                        + "  [T][ ] read book",
                new Parser(taskList, new Storage("")).parse("unmark 1"));
    }

    @Test
    public void parse_basic_delete() {
        TaskList taskList = new TaskList();
        taskList.readFromFile(new Todo("read book"));
        assertEquals("Noted. I've removed this task:\n"
                        + "  [T][ ] read book\n"
                        + "Now you have 0 task in the list.",
                new Parser(taskList, new Storage("")).parse("delete 1"));
    }
}
