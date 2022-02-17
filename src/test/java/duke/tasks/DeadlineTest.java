package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;

public class DeadlineTest {
    private Deadline deadline;

    @Test
    public void testCorrectDeadlineString() {
        String deadlineExpected = "[D][ ] CS3243 Assignment (by: 20-Feb-22 23:59)";
        String deadlineActual = "";

        try {
            deadline = new Deadline("CS3243 Assignment", "20-02-2022 23:59");
            deadlineActual = deadline.toString();
        } catch (DukeException duke) {
            Assertions.fail();
        }
        assertEquals(deadlineActual, deadlineExpected);
    }


    @Test
    public void testWrongDeadlineString() {
        String deadlineExpected = "The date format parsed is incorrect! It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!";
        String deadlineActual = "";

        try {
            deadline = new Deadline("Eat apple", "02/05/2021");
        } catch (DukeException duke) {
            deadlineActual = duke.toString();
        } finally {
            assertEquals(deadlineActual, deadlineExpected);
        }
    }

}
