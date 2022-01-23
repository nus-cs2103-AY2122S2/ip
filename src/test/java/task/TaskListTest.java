package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    private static TaskList testTaskList;

    @BeforeEach
    public void setUpTaskList() {
        testTaskList = new TaskList();
        testTaskList.addTask(new Todo("test"));
    }

    @Test
    public void addTask_successful() {
        testTaskList.addTask(new Todo("test"));
        Assertions.assertEquals(2, testTaskList.getTasks().size());
    }

    @Test
    public void markTaskAsDone_successful() {
        try {
            testTaskList.markTaskAsDone(0);
            Assertions.assertEquals(true, testTaskList.getTaskAtIndex(0).isDone());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void unmarkTaskAsDone_successful() {
        try {
            testTaskList.unmarkTaskAsDone(0);
            Assertions.assertEquals(false, testTaskList.getTaskAtIndex(0).isDone());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }

    @Test
    public void deleteTaskAtIndex_successful() {
        try {
            testTaskList.deleteTaskAtIndex(0);
            Assertions.assertEquals(0, testTaskList.getTasks().size());
        } catch (Exception e) {
            fail("Should not have thrown any exception");
        }
    }
}
