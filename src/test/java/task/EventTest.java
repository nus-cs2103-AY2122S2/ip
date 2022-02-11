package task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import karen.task.Event;

public class EventTest {
    protected static Event testEVENT_;
    protected static Event testMarkEVENT_;

    @BeforeAll
    public static void setUp() {
        testEVENT_ = new Event("example",
                LocalDateTime.of(2021, 1, 1, 6, 30));
        testMarkEVENT_ = new Event("example",
                LocalDateTime.of(2021, 1, 1, 18, 30));
        testMarkEVENT_.markDone();
    }

    @Test
    public void toSaveData_success() {
        assertEquals(
            testEVENT_.toSaveData(), "E|false|example|2021-01-01 0630"
        );
        assertEquals(
                testMarkEVENT_.toSaveData(), "E|true|example|2021-01-01 1830"
        );
    }

    @Test
    public void toString_success() {
        assertEquals(
                testEVENT_.toString(), "[E][ ] example (at: 2021-01-01 06:30 AM)"
        );
        assertEquals(
                testMarkEVENT_.toString(), "[E][X] example (at: 2021-01-01 06:30 PM)"
        );
    }

    @AfterAll
    public static void tearDown() {
        testEVENT_ = null;
        testMarkEVENT_ = null;
    }
}
