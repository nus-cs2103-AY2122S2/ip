package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeadlinesTest {

    private Deadlines d;

    @BeforeEach
    public void setUp() {
        d = new Deadlines("testing task", "2022-12-11");
    }

    @Test
    public void testShow() {
        assertEquals("[D][ ] testing task (by: 2022-12-11)", d);
    }

    @Test
    public void testStoreFormat() {
        assertEquals("D|false|testing task|2022-12-11\n", d.storeFormat());
    }
}
