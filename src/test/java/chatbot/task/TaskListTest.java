package chatbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testFind() {
        TaskList taskList = TaskList.create();
        taskList.add(new ToDo("1234"));
        taskList.add(new ToDo("key"));
        taskList.add(new ToDo("monkey"));
        taskList.add(new ToDo("ABCD"));
        taskList.add(new ToDo("The quick brown fox jumped over the xxkeyvvvv lazy dog."));

        Task[] tasks = taskList.find("key");
        assertEquals(tasks.length, 3);
        assertEquals(tasks[0].toString(), "[T][ ] key");
        assertEquals(tasks[1].toString(), "[T][ ] monkey");
        assertEquals(tasks[2].toString(), "[T][ ] The quick brown fox jumped over the xxkeyvvvv lazy dog.");
    }
}
