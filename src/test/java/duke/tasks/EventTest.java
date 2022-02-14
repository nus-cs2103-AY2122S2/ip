package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void getInitialTest(){
        assertEquals(Event.getInitial(), "E");
    }

    @Test
    public void getTime(){
        Event TestEvent = new Event("Dummy Description", "2020-01-18");
        assertEquals(TestEvent.getTime(), "2020-01-18");
    }

    @Test
    public void getFormattedTime(){
        Event TestEvent = new Event("Dummy Description", "2020-01-18");
        assertEquals(TestEvent.getFormattedTime(), "Jan 18 2020");
    }

    @Test
    public void getDescriptionTest(){
        Event TestEvent = new Event("Dummy Description", "2020-01-18");
        assertEquals(TestEvent.getDescription(), "Dummy Description");
    }

}
