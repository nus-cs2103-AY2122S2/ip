package duke.tasks;

import duke.ui.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void saveToFileString_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new ToDo("Homework").saveToFileString();
        String actualResult = "T|0|Homework\n";
        assertEquals(expectedResult, actualResult);
    }


    @Test
    public void toString_callingMethod_outputShownCorrectly() throws DukeException {
        String expectedResult = new ToDo("Homework").toString();
        String actualResult = "[T][ ] Homework";
        assertEquals(expectedResult, actualResult);
    }
}
