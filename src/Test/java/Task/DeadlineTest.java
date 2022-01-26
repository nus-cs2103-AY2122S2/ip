package Task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToSave() {
        assertEquals(
                "D : 0 : work : 2022-02-02 06:30",
                new Deadline("work", "2022-02-02 06:30").toSave());
    }

    @Test
    public void testToString() {
        assertEquals(
                "[D][ ] work (by: 2022-02-02 06:30)",
                new Deadline("work", "2022-02-02 06:30").toString());
    }
}