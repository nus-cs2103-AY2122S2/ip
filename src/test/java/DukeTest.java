import commands.AddCommand;
import commands.ExitCommand;
import common.Type;
import data.Task;
import org.junit.jupiter.api.Test;
import parser.Parser;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DukeTest {
    @Test
    public void taskTest1() {
        Task t = new Task("do something", Type.EVENT);
        assertEquals(t, (new Task("do something", Type.EVENT)));
    }

    @Test
    public void taskTest2() {
        Task t = new Task("do something", Type.EVENT);
        assertNotEquals(t, (new Task("do something", Type.DEADLINE)));
    }

    @Test
    public void eventTest() {
        Task t = new Task("do something", Type.EVENT);
        t.setTime("2022-1-28 9am");
        assertEquals(Parser.parse("event do something /at 2022-1-28 9am"),
                new AddCommand(t));
    }

    @Test
    public void deadLineTest() {
        Task t = new Task("do something", Type.DEADLINE);
        t.setTime("2022-1-28 9am");
        assertEquals(Parser.parse("deadline do something /by 2022-1-28 9am"),
                new AddCommand(t));
    }

    @Test
    public void toDoTest() {
        Task t = new Task("do something", Type.TODO);
        assertEquals(Parser.parse("todo do something"),
                new AddCommand(t));
    }

    @Test
    public void exitTest() {
        assertEquals(Parser.parse("bye"),
                new ExitCommand());
    }
}