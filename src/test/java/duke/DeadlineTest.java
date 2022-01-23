package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToString() {
        assertEquals("[D][X] do week 3 iP (by: Jan 23 2022)", new Deadline(" do week 3 iP", true, "2022-01-23").toString());
    }
}
