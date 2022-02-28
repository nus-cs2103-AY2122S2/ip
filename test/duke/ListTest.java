package duke;

import duke.task.Task;
import duke.task.TaskStub;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {

    ArrayList<Task> testArrList = new ArrayList<>();
    TaskStub taskStub = new TaskStub("taskTest", false);

    @Test
    void getLast() {
        testArrList.add(taskStub);
        List listTest = new List(testArrList);
        assertEquals("taskTest", listTest.getLast().getDescription());
    }
}