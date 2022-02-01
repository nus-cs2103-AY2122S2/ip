package stevie.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

import stevie.exception.TaskException;

class TaskCreatorTest {

    @Test
    void create() {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("24/01/2022 13:00");
            assertEquals(new EventTask("cs2103 lecture", date).toString(),
                    TaskCreator.create(TaskType.Event, false, "cs2103 lecture", date).toString());
        } catch (TaskException | ParseException ex) {
            // Should not receive any exceptions
        }
    }

    @Test
    void charToType() throws TaskException {
        assertEquals(TaskCreator.charToType('E'), TaskType.Event);
    }
}
