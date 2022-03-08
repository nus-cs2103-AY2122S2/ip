import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Event;

public class EventTest {
    @Test
    public void testEmptyInit() {
        Event event = new Event();

        assertEquals("[E][ ]  (at: Dec 12 2020 11:59 PM)", event.toString());
    }

    @Test
    public void testFullInit() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        Event event = new Event(
                "Party at Tom's", LocalDate.parse("2015-10-05"), LocalTime.parse("1505", timeFormatter));

        assertEquals("[E][ ] Party at Tom's (at: Oct 5 2015 03:05 PM)", event.toString());
    }

    @Test
    public void extractFileDataMarkTrue() throws DukeException {
        Event event = new Event();
        String data = "T|true|Cycling Competition|Dec 12 2022|09:05 AM";

        event.extractDataFromLine(data);
        assertEquals("[E][X] Cycling Competition (at: Dec 12 2022 09:05 AM)", event.toString());
    }

    @Test
    public void extractFileDataMarkFalse() throws DukeException {
        Event event = new Event();
        String data = "T|false|Swim meet|Nov 1 2022|05:15 AM";

        event.extractDataFromLine(data);
        assertEquals("[E][ ] Swim meet (at: Nov 1 2022 05:15 AM)", event.toString());
    }

    @Test
    public void saveDataMarkTrue() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        Event event = new Event("Final exam", LocalDate.parse("2015-10-05"), LocalTime.parse("1505", timeFormatter));
        event.setIsDone(true);

        assertEquals("E|true|Final exam|Oct 5 2015|03:05 PM\n", event.saveFileFormat());
    }

    @Test
    public void saveDataMarkFalse() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        Event event = new Event("Final exam", LocalDate.parse("2015-10-05"), LocalTime.parse("1505", timeFormatter));

        assertEquals("E|false|Final exam|Oct 5 2015|03:05 PM\n", event.saveFileFormat());
    }
}
