import com.duke.modules.TaskList;
import com.duke.tasks.Task;
import com.duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTasklistTest {
    @Test
    public void displayFilledListTest() {
        TaskList testModule = new TaskList();
        ArrayList<Task> testList = new ArrayList<>();
        Task testTask = new Todo("testing");
        testList.add(testTask);
        testModule.setListWithoutSaving(testList);
        assertEquals(testModule.displayList(), "1. [T][ ] testing");
    }

    @Test
    public void displayEmptyListTest() {
        TaskList testModule = new TaskList();
        ArrayList<Task> testList = new ArrayList<>();
        testModule.setListWithoutSaving(testList);
        assertEquals(testModule.displayList(), "LUMU: Your list is empty!");
    }
}
