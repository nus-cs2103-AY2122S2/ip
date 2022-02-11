package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void parserTest() {
        Ui ui = new Ui();
        assertEquals(Parser.parse("list", ui).getFirstWord(), "list");
        assertEquals(Parser.parse("list test", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("todo", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("todo test", ui).getFirstWord(), "todo");
        assertEquals(Parser.parse("deadline", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("deadline /by", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("deadline test /by", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("deadline test/by 2020=-03-04", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("deadline test /by 2020-03-04", ui).getFirstWord(), "deadline");
        assertEquals(Parser.parse("deadline test /by 2020", ui).getFirstWord(), "deadline");
        assertEquals(Parser.parse("event", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("event /at", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("event test /at", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("event test/at 2020=-03-04", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("event test /at 2020-03-04", ui).getFirstWord(), "event");
        assertEquals(Parser.parse("event test /at 2020", ui).getFirstWord(), "event");
        assertEquals(Parser.parse("mark", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("mark test", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("unmark", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("unmark test", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("mark 1", ui).getFirstWord(), "mark");
        assertEquals(Parser.parse("unmark 1", ui).getFirstWord(), "unmark");
        assertEquals(Parser.parse("delete", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("delete test", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("delete 1", ui).getFirstWord(), "delete");
        assertEquals(Parser.parse("find", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("find test", ui).getFirstWord(), "find");
        assertEquals(Parser.parse("bye", ui).getFirstWord(), "bye");
        assertEquals(Parser.parse("bye test", ui).getFirstWord(), "Please try again!");
        assertEquals(Parser.parse("test", ui).getFirstWord(), "Please try again!");
    }

    @Test
    public void taskTest() {
        assertEquals(new Todo("test").toString(), "[T][ ] test");
        assertEquals(new Todo("test").unmark().toString(), "[T][ ] test");
        assertEquals(new Todo("test").mark().toString(), "[T][X] test");
        assertEquals(new Event("test", "2020-03-04").toString(), "[E][ ] test (at: Mar 4 2020)");
        assertEquals(new Event("test", "2020-03-04").unmark().toString(), "[E][ ] test (at: Mar 4 2020)");
        assertEquals(new Event("test", "2020-03-04").mark().toString(), "[E][X] test (at: Mar 4 2020)");
        assertEquals(new Deadline("test", "2020-03-04").toString(), "[D][ ] test (by: Mar 4 2020)");
        assertEquals(new Deadline("test", "2020-03-04").unmark().toString(), "[D][ ] test (by: Mar 4 2020)");
        assertEquals(new Deadline("test", "2020-03-04").mark().toString(), "[D][X] test (by: Mar 4 2020)");
    }
}
