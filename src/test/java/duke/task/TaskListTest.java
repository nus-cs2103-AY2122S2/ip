package duke.task;

import duke.util.Storing;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void isFileHasTextTest() throws IOException {
        Storing storingReal = new StoringStub("data/duke.Duke.txt", "data");
        Storing storingFake = new StoringStub("test/duke.Duke.txt", "test");
        TaskList taskListReal = new TaskList(storingReal);
        TaskList taskListFake = new TaskList(storingFake);
        assertEquals(true, taskListReal.isFileHasText());
        assertEquals(false, taskListFake.isFileHasText());
    }

    @Test
    public void addTaskTest() {
        Storing storing = new StoringStub("data/duke.Duke.txt", "data");
        TaskList taskList = new TaskList(storing);
        assertEquals(0, taskList.getTotalTask());
        taskList.addTask(new Task("return book /by 2/12/2019", "D"));
        assertEquals(1, taskList.getTotalTask());
    }

    @Test
    public void deleteTaskTest() {
        Storing storing = new StoringStub("data/duke.Duke.txt", "data");
        TaskList taskList = new TaskList(storing);
        taskList.addTask(new Task("return book /by 2/12/2019", "D"));
        taskList.addTask(new Task("return book /by 2/12/2019", "D"));
        taskList.removeTask(1);
        assertEquals(1, taskList.getTotalTask());
    }

}
