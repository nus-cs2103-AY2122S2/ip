package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToSave() {
        assertEquals(
                "E : 0 : work : 2022-02-02 06:30",
                new Event("work", "2022-02-02 06:30").toSave());
    }

    @Test
    public void testToString() {
        assertEquals(
                "[E][ ] work (by: 2022-02-02 06:30)",
                new Event("work", "2022-02-02 06:30").toString());
    }
}