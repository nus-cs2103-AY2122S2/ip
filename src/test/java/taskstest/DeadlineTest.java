package taskstest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mytasks.Deadline;

public class DeadlineTest {
    @Test
    void toStringTest() {
        Deadline d = new Deadline("help mom", "2022-10-10 10PM");
        assertEquals("[D][ ] help mom (by: October 10, 2022 10PM)", d.toString());
    }
}
