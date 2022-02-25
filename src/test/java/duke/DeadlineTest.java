package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeadlineTest {

    @Test
    public void DeadlineTest() {
        String test1 = "help Kris do cs2103";
        String answer1 = "[D][ ] help Kris do cs2103 (by: Mar 2 2001 06:00 PM)";
        assertEquals(answer1, new Deadline(test1, "Mar 2 2001 06:00 PM").toString());
    }
    
}