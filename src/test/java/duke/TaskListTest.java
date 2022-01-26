package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void setTaskAsDone_initiallyUndoneTask_success() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.add(new Task("todo eat"));

        TaskList tasks = new TaskList(taskList);

        tasks.setTaskAsDone(1);
        assertEquals(taskList.get(0).toString(), "[X] todo eat");
    }

}
