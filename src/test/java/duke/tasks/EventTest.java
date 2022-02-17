package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.ui.DukeException;

public class EventTest {

    @Test
    public void encodeTaskToString_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new Event("Team meeting", "2/12/2019 6PM").encodeTaskToString();
        String actualResult = "E|0|Team meeting|2019-12-02 18:00\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void getTaskDate_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new Event("Seminar", "2/12/2019 0900").encodeTaskToString();
        String actualResult = "E|0|Seminar|2019-12-02 09:00\n";
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void toString_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new Event("Team meeting", "2/12/2019 6PM").toString();
        String actualResult = "[E][ ] Team meeting (at: Dec 02 2019 18:00)";
        assertEquals(expectedResult, actualResult);
    }
}
