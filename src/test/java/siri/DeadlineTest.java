package siri;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void getItemAndStatus() {
        Deadline dlTask = new Deadline("return book", 0, LocalDate.now());
        String expectedOutput = "[D][ ] return book" + " (by: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd-LLL-yyyy"))+ ")";
        assertEquals(expectedOutput, dlTask.getItemAndStatus());
    }
}