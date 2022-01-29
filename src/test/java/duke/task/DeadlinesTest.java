package duke.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    Deadlines d;

    @BeforeEach
    public void setUp(){
        d = new Deadlines("testing task", "2022-12-11");
    }

    @Test
    public void testShow(){
        assertEquals("[D][ ] testing task (by: 2022-12-11)", d.show());
    }

    @Test
    public void testStoreFormat(){
        assertEquals("D|false|testing task|2022-12-11\n", d.storeFormat());
    }
}
