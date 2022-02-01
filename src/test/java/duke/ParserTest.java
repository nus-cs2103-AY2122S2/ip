package seedu.duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getDescription() {
        Task t = new ToDo("description");
        try {
            assertEquals("description", Parser.getDescription("todo description"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    void getTask() {
        String saved = "E#false#party #2022-01-22";
        try {
            assertEquals((new Event("party ", LocalDate.of(2022, 1, 22))).toString(), Parser.getTask(saved).toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}