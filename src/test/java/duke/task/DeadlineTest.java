package duke.task;

import duke.task.tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void getStatusIconTest() {
        Deadline deadline = new Deadline("eat", "2019-12-12");
        String icon = deadline.getStatusIcon();
        assertEquals(icon, " ");

        Deadline checkedDeadline = new Deadline("eat", true,"2019-12-12" );
        icon = checkedDeadline.getStatusIcon();
        assertEquals(icon, "X");
    }

    @Test
    public void markTest() {
        Deadline deadline = new Deadline("eat", "2019-12-12");
        String icon = deadline.getStatusIcon();
        assertEquals(icon, " ");

        deadline.switchMark("mark");
        icon = deadline.getStatusIcon();
        assertEquals(icon, "X");
    }

    @Test
    public void unmarkTest() {
        Deadline checkedDeadline = new Deadline("eat", true,"2019-12-12" );
        String icon = checkedDeadline.getStatusIcon();
        assertEquals(icon, "X");

        checkedDeadline.switchMark("unmark");
        icon = checkedDeadline.getStatusIcon();
        assertEquals(icon, " ");
    }
}
