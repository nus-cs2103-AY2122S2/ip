import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testEmptyInit() {
        Deadline deadline = new Deadline();

        assertEquals("[D][ ]  (by: Dec 12 2020 11:59 PM)", deadline.toString());
    }

    @Test
    public void testFullInit() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        Deadline deadline = new Deadline(
                "Finish CS2300 Assignment", LocalDate.parse("2015-10-05"), LocalTime.parse("1505", timeFormatter));

        assertEquals("[D][ ] Finish CS2300 Assignment (by: Oct 5 2015 03:05 PM)", deadline.toString());
    }

    @Test
    public void extractFileDataMarkTrue() {
        Deadline deadline = new Deadline();
        String data = "T|true|Cycle 15km|Dec 12 2022|09:05 AM";

        deadline.extractDataFromLine(data);
        assertEquals("[D][X] Cycle 15km (by: Dec 12 2022 09:05 AM)", deadline.toString());
    }

    @Test
    public void extractFileDataMarkFalse() {
        Deadline deadline = new Deadline();
        String data = "T|false|Swim 5km|Nov 1 2022|05:15 AM";

        deadline.extractDataFromLine(data);
        assertEquals("[D][ ] Swim 5km (by: Nov 1 2022 05:15 AM)", deadline.toString());
    }

    @Test
    public void saveDataMarkTrue() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        Deadline deadline = new Deadline(
                "Finish CS2300 Assignment", LocalDate.parse("2015-10-05"), LocalTime.parse("1505", timeFormatter));
        deadline.setIsDone(true);

        assertEquals("D|true|Finish CS2300 Assignment|Oct 5 2015|03:05 PM\n", deadline.saveFileFormat());
    }

    @Test
    public void saveDataMarkFalse() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        Deadline deadline = new Deadline(
                "Finish CS2300 Assignment", LocalDate.parse("2015-10-05"), LocalTime.parse("1505", timeFormatter));

        assertEquals("D|false|Finish CS2300 Assignment|Oct 5 2015|03:05 PM\n", deadline.saveFileFormat());
    }
}
