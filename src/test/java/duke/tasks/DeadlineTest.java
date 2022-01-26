package duke.tasks;

import duke.ui.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void saveToFileString_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new Deadline("IP Project", "2/12/2019 6pm").saveToFileString();
        String actualResult = "D|0|IP Project|2019-12-02 18:00\n";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void saveToFileString_callingMethodWithDiffInput_outputShownCorrectly() throws DukeException {
        String expectedResult = new Deadline("return book", "2/12/2019 0900").saveToFileString();
        String actualResult = "D|0|return book|2019-12-02 09:00\n";
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void toString_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new Deadline("IP Project", "2/12/2019 6pm").toString();
        String actualResult = "[D][ ] IP Project (by: Dec 02 2019 18:00)";
        assertEquals(expectedResult, actualResult);
    }


}
