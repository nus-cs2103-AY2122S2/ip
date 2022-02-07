package duke;

import org.junit.test;
import static org.junit.assertEquals;
import duke.duke.Deadline;

class DeadlineTest {

    @Test
    public void DeadlineTest() {
        String test1 = "deadline help Kris do cs3132 /by 2/3/2001 1800";
        String answer1 = "[D][ ] help Kris do cs2103 (by: Mar 2 2001 06:00 PM)"
        assertEquals(answer1, new Deadline(test1, "2/3/2001 1800"));
    }
    
}