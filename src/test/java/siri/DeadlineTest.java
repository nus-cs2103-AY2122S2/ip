package siri;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void getTaskDetails_noTimeField() {
        Deadline dlTask = new Deadline("return book", false, LocalDate.now());
        String expectedOutput = "[D][ ] return book" + " (by: "
                + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-LLL-yyyy")) + ")";
        assertEquals(expectedOutput, dlTask.getTaskDetails());
    }
}
