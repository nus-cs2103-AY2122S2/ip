package task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void symbolTest(){
        Event testEvent = new Event("Complete 21003 ip tasks", "2021-01-30");
        assertEquals(testEvent.symbol(), "E");
    }

    @Test
    public void displayTimeTest() {
        Event testEvent = new Event("Complete 21003 ip tasks", "2021-01-30");
        assertEquals(testEvent.displayTime(), "Complete 21003 ip tasks30 JANUARY 2021");
    }
}
