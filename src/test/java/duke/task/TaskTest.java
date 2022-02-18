package duke.task;

import duke.task.FailedToInterpretTaskException;
import duke.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    /**
     * Tests the correctness of the factory method in Task.
     */
    @Test
    void generateTaskFromStringTest() {

        String[] instruction = {
                "deadline project /by 2022-02-02",
                "todo homework",
                "event meeting /at 3pm"
        };

        String[] expectedStringRepresentation = {
                "[D][ ] project (by Feb 2, 2022)",
                "[T][ ] homework",
                "[E][ ] meeting (at 3pm)"
        };

        try {
            for (int i = 0; i < instruction.length; i++) {
                Assertions.assertEquals(Task.of(instruction[i]).toString(), expectedStringRepresentation[i],
                        "Test case " + i + 1 + "failed.");
            }
        } catch (FailedToInterpretTaskException e) {
            fail("Task.of() throws an unexpected exception:" + e.getMessage());
        }

        String[] invalidInputs = {
                "deadline abc /by 3pm",
                "event e /by 2-5pm",
                "todo",
                "deadline by 2021-09-03"
        };

        String[] expectedExceptionMessage = {
                "Oops, the format of date should be YYYY-MM-DD!",
                "Oops, both of the description and the time of the event can't be empty.",
                "Oops, a type and a description for the task must be provided.",
                "Oops, both of the description and the due time of the deadline can't be empty."
        };

        for (int i = 0; i < invalidInputs.length; i++) {
            final int index = i;
            FailedToInterpretTaskException e =
                    assertThrows(FailedToInterpretTaskException.class, () -> Task.of(invalidInputs[index]),
                            "Test case " + i + 1 + instruction.length + ": didn't throw expected exception.");
            assertEquals(e.getMessage(), expectedExceptionMessage[i],
                    "Test case " + i + 1 + instruction.length + ": wrong exception message.");
        }
    }
}