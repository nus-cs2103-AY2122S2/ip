package pac.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_validInput_validString() {
        assertEquals("[D][ ] get a haircut (by: Mar 15 2022)",
                new Deadline("get a haircut", "2022-03-15").toString());
    }

    @Test
    public void toWrite_validInput_validString() {
        assertEquals("D~0~get a haircut~2022-03-15" + System.lineSeparator(),
                new Deadline("get a haircut", "2022-03-15").toWrite());
    }
}
