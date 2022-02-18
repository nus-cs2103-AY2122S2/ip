package tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    /**
     * tests of task is returned properly.
     */
    @Test
    public void getTask_taskInstance_taskStored() {

        Task case1 = new Task("return books to library");
        assertEquals("return books to library", case1.getTask());

        Task case2 = new Task("Cook pasta for dinner");
        assertEquals("Cook pasta for dinner", case2.getTask());

    }

    /**
     * tests if isOnDate returns false.
     */
    @Test
    public void isOnDate_taskInstance_false() {
        Task case1 = new Task("return books to library");
        assertEquals(false, case1.isOnDate("2022-08-15"));

        Task case2 = new Task("Cook pasta for dinner");
        assertEquals(false, case2.isOnDate("2022-08-15"));
    }

    /**
     * tests if isBefore returns false.
     */
    @Test
    public void isBefore_taskInstance_false() {
        Task case1 = new Task("return books to library");
        assertEquals(false, case1.isBefore("2022-08-15"));

        Task case2 = new Task("Cook pasta for dinner");
        assertEquals(false, case2.isBefore("2022-08-15"));
    }
}
