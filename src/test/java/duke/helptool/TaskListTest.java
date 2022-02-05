package duke.helptool;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;
import duke.tag.Tag;

public class TaskListTest {

    private final ArrayList<Task> tasks = new ArrayList<>();
    private final TaskList taskList = new TaskList();

    @Test
    @DisplayName("Determine size should work")
    void testTaskListSize() {
        assertEquals(tasks.size(), taskList.getSize(), "Default should be empty");
        Tag tag = new Tag("");
        ToDo temp = new ToDo("hi", tag);
        tasks.add(temp);
        taskList.addTask(temp);
        assertEquals(tasks.size(), taskList.getSize(), "Add is successful");
        tasks.remove(0);
        taskList.delete(0);
        assertEquals(tasks.size(), taskList.getSize(), "Delete is successful");
    }

    @Test
    @DisplayName("Determine add should work")
    void testTaskListGet() {
        Tag tag = new Tag("");
        ToDo temp = new ToDo("hi", tag);
        tasks.add(temp);
        taskList.addTask(temp);
        assertEquals(tasks.get(0), taskList.getTask(0), "Add is successful");
        tasks.remove(0);
        taskList.delete(0);
    }
}
