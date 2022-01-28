package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

/**
 * This class tests methods from the Task class.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class TaskTest {

    /**
     *  Tests the changeFormat method in the task class.
     */
    @Test
    public void testChangeFormat() {
        LocalDate date = LocalDate.parse("2020-03-01");

        // First Testcase
        Task tc1 = new ToDo("Science Assignment", false);
        assertEquals(tc1.changeFormat(), "T,0,Science Assignment");

        // Second Testcase
        Task tc2 = new ToDo("Math Assignment", true);
        assertEquals(tc2.changeFormat(), "T,1,Math Assignment");

        // Third Testcase
        Task tc3 = new Deadline("Reading Journal", date, false);
        assertEquals(tc3.changeFormat(), "D,0,Reading Journal,2020-03-01");

        // Fourth Testcase
        Task tc4 = new Deadline("History Homework", date, true);
        assertEquals(tc4.changeFormat(), "D,1,History Homework,2020-03-01");

        // Fifth Testcase
        Task tc5 = new Event("New Year", date, false);
        assertEquals(tc5.changeFormat(), "E,0,New Year,2020-03-01");

        // Sixth Testcase
        Task tc6 = new Event("Christmas", date, true);
        assertEquals(tc6.changeFormat(), "E,1,Christmas,2020-03-01");
    }

}
