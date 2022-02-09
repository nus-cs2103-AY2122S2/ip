import duke.Parser;
import duke.TaskList;
import duke.Todo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static final PrintStream STDOUT = System.out;
    private static final PrintStream STDERR = System.err;
    private static final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
    private static final String SEG_LINE = "    ____________________________________________________________";
    private static final String INDENT = "    ";
    private static final String BYE_PHRASE = SEG_LINE + System.lineSeparator()
            + INDENT + "Bye. Hope to see you again soon!" + System.lineSeparator() + SEG_LINE + System.lineSeparator();

    @BeforeAll
    public static void resetOutput() {
        System.setOut(new PrintStream(myOut));
        System.setErr(new PrintStream(myOut));
    }

    @AfterAll
    public static void restoreOutput() {
        System.setOut(STDOUT);
        System.setErr(STDERR);
    }

    @Test
    public void dummyTest() {
        myOut.reset();
        System.out.println("test stdout");
        assertEquals("test stdout" + System.lineSeparator(), myOut.toString());
        myOut.reset();
        assertEquals("", myOut.toString());
    }

    @Test
    public void parse_basic_list() {
        myOut.reset();
        TaskList taskList = new TaskList();
        new Parser(new Scanner("list\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "You don't have tasks listed." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());

        myOut.reset();
        taskList.readFromFile(new Todo("read book"));
        new Parser(new Scanner("list\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Here are the tasks in your list:" + System.lineSeparator()
                        + INDENT + "1.[T][ ] read book" + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());
    }

    @Test
    public void parse_basic_todo() {
        myOut.reset();
        TaskList taskList = new TaskList();
        new Parser(new Scanner("todo read book\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Got it. I've added this task:" + System.lineSeparator()
                        + INDENT + "  [T][ ] read book" + System.lineSeparator()
                        + INDENT + "Now you have 1 task in the list." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());

        myOut.reset();
        new Parser(new Scanner("todo have lunch\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Got it. I've added this task:" + System.lineSeparator()
                        + INDENT + "  [T][ ] have lunch" + System.lineSeparator()
                        + INDENT + "Now you have 2 tasks in the list." + System.lineSeparator()
                        + SEG_LINE + "\n" + BYE_PHRASE,
                myOut.toString());
    }

    @Test
    public void parse_basic_deadline() {
        myOut.reset();
        TaskList taskList = new TaskList();
        new Parser(new Scanner("deadline read book /by 2022-01-02\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Got it. I've added this task:" + System.lineSeparator()
                        + INDENT + "  [D][ ] read book (by: 02 Jan 2022)" + System.lineSeparator()
                        + INDENT + "Now you have 1 task in the list." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());

        myOut.reset();
        new Parser(new Scanner("deadline have lunch /by 2022-01-03\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Got it. I've added this task:" + System.lineSeparator()
                        + INDENT + "  [D][ ] have lunch (by: 03 Jan 2022)" + System.lineSeparator()
                        + INDENT + "Now you have 2 tasks in the list." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());
    }

    @Test
    public void parse_basic_event() {
        myOut.reset();
        TaskList taskList = new TaskList();
        new Parser(new Scanner("event read book /at 2-4pm\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Got it. I've added this task:" + System.lineSeparator()
                        + INDENT + "  [E][ ] read book (at: 2-4pm)" + System.lineSeparator()
                        + INDENT + "Now you have 1 task in the list." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());

        myOut.reset();
        new Parser(new Scanner("event have lunch /at 4-6pm\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Got it. I've added this task:" + System.lineSeparator()
                        + INDENT + "  [E][ ] have lunch (at: 4-6pm)" + System.lineSeparator()
                        + INDENT + "Now you have 2 tasks in the list." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());
    }

    @Test
    public void parse_basic_mark() {
        myOut.reset();
        TaskList taskList = new TaskList();
        taskList.readFromFile(new Todo("read book"));
        new Parser(new Scanner("mark 1\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Nice! I've marked this task as done:" + System.lineSeparator()
                        + INDENT + "  [T][X] read book" + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());
    }

    @Test
    public void parse_basic_unmark() {
        myOut.reset();
        TaskList taskList = new TaskList();
        taskList.readFromFile(new Todo("read book"));
        new Parser(new Scanner("unmark 1\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "OK, I've marked this task as not done yet:" + System.lineSeparator()
                        + INDENT + "  [T][ ] read book" + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());
    }

    @Test
    public void parse_basic_delete() {
        myOut.reset();
        TaskList taskList = new TaskList();
        taskList.readFromFile(new Todo("read book"));
        new Parser(new Scanner("delete 1\nbye"), taskList).parse();
        assertEquals(SEG_LINE + System.lineSeparator()
                        + INDENT + "Noted. I've removed this task:" + System.lineSeparator()
                        + INDENT + "  [T][ ] read book" + System.lineSeparator()
                        + INDENT + "Now you have 0 task in the list." + System.lineSeparator()
                        + SEG_LINE + System.lineSeparator() + BYE_PHRASE,
                myOut.toString());
    }
}
