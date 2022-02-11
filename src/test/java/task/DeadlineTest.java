package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import karen.task.Deadline;

public class DeadlineTest {
    protected static Deadline testDEADLINE_;
    protected static Deadline testMarkDEADLINE_;

    @BeforeAll
    public static void setUp() {
        testDEADLINE_ = new Deadline("example",
                LocalDateTime.of(2021, 1, 1, 6, 30));
        testMarkDEADLINE_ = new Deadline("example",
                LocalDateTime.of(2021, 1, 1, 18, 30));
        testMarkDEADLINE_.markDone();
    }

    @Test
    public void toSaveData_success() {
        assertEquals(
                testDEADLINE_.toSaveData(), "D|false|example|2021-01-01 0630"
        );
        assertEquals(
                testMarkDEADLINE_.toSaveData(), "D|true|example|2021-01-01 1830"
        );
    }

    @Test
    public void toString_success() {
        assertEquals(
                testDEADLINE_.toString(), "[D][ ] example (by: 2021-01-01 06:30 AM)"
        );
        assertEquals(
                testMarkDEADLINE_.toString(), "[D][X] example (by: 2021-01-01 06:30 PM)"
        );
    }

    @AfterAll
    public static void tearDown() {
        testDEADLINE_ = null;
        testMarkDEADLINE_ = null;
    }
}
