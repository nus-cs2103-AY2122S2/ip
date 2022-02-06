package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringOutput_descDateTime() {
        Todo deadline = new Todo("cook");
        assertEquals("[T][ ]cook", deadline.toString());
    }
}
