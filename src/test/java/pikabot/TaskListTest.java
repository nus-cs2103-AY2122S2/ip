package pikabot;

import org.junit.jupiter.api.Test;
import pikabot.task.Task;
import pikabot.task.Todo;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    TaskList tasklist;

    public void initialiseDummy() {    //Dummy taskList containing a task
        ArrayList<Task> arrList = new ArrayList<>();
        Todo todo = new Todo("Testing!");
        arrList.add(todo);
        this.tasklist = new TaskList(arrList);
    }

    @Test
    public void addedTaskToList_success() {
        initialiseDummy();
        this.tasklist.add(new Todo("Test again!"));
        String addedTaskStr = this.tasklist.get(1).toString();
        assertEquals("[T][ ] Test again!", addedTaskStr);
    }

    @Test
    public void markTaskInTaskList_success() {
        initialiseDummy();
        this.tasklist.markTaskAsDone(1);
        String todoStr = this.tasklist.get(0).toString();
        assertEquals("[T][X] Testing!", todoStr);
    }

}
