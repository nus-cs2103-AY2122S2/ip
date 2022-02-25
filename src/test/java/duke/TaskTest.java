package duke;


import duke.tasks.Deadline;
import duke.tasks.Task;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    @DisplayName("String representation of Task is appropriate.")
    public void checkDisplayFormat() {
        Task curr = new Deadline("finish iP tasks", "18/02/2022 1400");
        curr.markDone();
        String expected = "[D][X] finish iP tasks(by: Feb 18 2022, 14:00)";
        String output = curr.toString();
        assertEquals(expected, output);
    }


}
