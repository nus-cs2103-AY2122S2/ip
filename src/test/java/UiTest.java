import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import karen.Ui;
import karen.task.Deadline;
import karen.task.Event;
import karen.task.Task;
import karen.task.ToDo;

public class UiTest {
    protected static Ui testUI_;
    protected static ToDo testTODO_;
    protected static Deadline testDEADLINE_;
    protected static Event testEVENT_;

    @BeforeAll
    public static void setUpUi() {
        testUI_ = new Ui();
        testTODO_ = new ToDo("example");
        testDEADLINE_ = new Deadline("example", LocalDateTime.of(2021, 1, 1, 18, 30));
        testEVENT_ = new Event("example", LocalDateTime.of(2021, 1, 1, 18, 30));
    }

    @Test
    public void displayUserInput() {
        assertEquals(testUI_.displayUserInput("hello"), "hello");
    }

    @Test
    public void formatEditTask() {
        assertEquals(testUI_.formatEditTask(testTODO_),
                "Fine. Task edited, looks like this now:\n[T][ ] example");
        assertEquals(testUI_.formatEditTask(testDEADLINE_),
                "Fine. Task edited, looks like this now:\n[D][ ] example (by: 2021-01-01 06:30 PM)");
        assertEquals(testUI_.formatEditTask(testEVENT_),
                "Fine. Task edited, looks like this now:\n[E][ ] example (at: 2021-01-01 06:30 PM)");
    }

    @Test
    public void formatCount() {
        assertEquals(testUI_.formatCount("added", testTODO_, 10),
                "Fine. Task added:\n [T][ ] example\nNow you have 10 in total.");
        assertEquals(testUI_.formatCount("added", testDEADLINE_, 1),
                "Fine. Task added:\n [D][ ] example (by: 2021-01-01 06:30 PM)\nNow you have 1 in total.");
        assertEquals(testUI_.formatCount("removed", testEVENT_, 2),
                "Fine. Task removed:\n [E][ ] example (at: 2021-01-01 06:30 PM)\nNow you have 2 in total.");
    }

    @Test
    public void formatTaskList() {
        ArrayList<Task> testList = new ArrayList<Task>();

        assertEquals(testUI_.formatTaskList(testList), "Nothing can be found.");

        testList.add(testTODO_);
        assertEquals(testUI_.formatTaskList(testList), "1.[T][ ] example\n");
        testList.add(testDEADLINE_);
        assertEquals(testUI_.formatTaskList(testList),
                "1.[T][ ] example\n2.[D][ ] example (by: 2021-01-01 06:30 PM)\n");
        testList.add(testEVENT_);
        assertEquals(testUI_.formatTaskList(testList),
                "1.[T][ ] example\n2.[D][ ] example (by: 2021-01-01 06:30 PM)\n"
                        + "3.[E][ ] example (at: 2021-01-01 06:30 PM)\n");
    }

    @AfterAll
    public static void tearDown() {
        testUI_ = null;
    }
}
