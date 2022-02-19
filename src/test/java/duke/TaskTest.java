package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


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
    public void testChangeFormat() throws DukeException{
        //Create Dummy taglist
        TagList tagList = new TagList();

        // First Testcase
        Task tc1 = new ToDo("Science Assignment", false);
        assertEquals(tc1.changeFormat(tagList), "T,0,Science Assignment");

        // Second Testcase
        Task tc2 = new ToDo("Math Assignment", true);
        assertEquals(tc2.changeFormat(tagList), "T,1,Math Assignment");

        // Third Testcase
        Task tc3 = new Deadline("Reading Journal", "2020-03-01", false);
        assertEquals(tc3.changeFormat(tagList), "D,0,Reading Journal,2020-03-01");

        // Fourth Testcase
        Task tc4 = new Deadline("History Homework", "2020-03-01", true);
        assertEquals(tc4.changeFormat(tagList), "D,1,History Homework,2020-03-01");

        // Fifth Testcase
        Task tc5 = new Event("New Year", "2020-03-01", false);
        assertEquals(tc5.changeFormat(tagList), "E,0,New Year,2020-03-01");

        // Sixth Testcase
        Task tc6 = new Event("Christmas", "2020-03-01", true);
        assertEquals(tc6.changeFormat(tagList), "E,1,Christmas,2020-03-01");
    }

}
