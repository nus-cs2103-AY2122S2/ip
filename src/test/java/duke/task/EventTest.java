package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void eventToString_validInput_validOutput() {
        assertEquals("E|false|hang out with friends|Vivo 2pm 2014-12-10|\n",
                new Event("hang out with friends", "Vivo 2pm 2014-12-10").toString());
    }
    @Test
    public void eventPrintTask_validInput_validOutput() {
        assertEquals("[E][ ] exam (at 2020-01-24)", new Event("exam", "2020-01-24").printTask());
    }
}
