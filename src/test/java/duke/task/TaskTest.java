package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {
    @Test
    void decodeTaskData_todoTaskString_returnTodoInstance() {
        String todoTestData = "T,Test TODO Task,N,NA";
        Task task = Task.decodeTaskData(todoTestData);
        assertTrue(task instanceof ToDo);
    }

    @Test
    void decodeTaskData_eventTaskString_returnEventInstance() {
        String eventTestData = "E,Test Event Task,N,Monday 2-4pm";
        Task task = Task.decodeTaskData(eventTestData);
        assertTrue(task instanceof Event);
    }

    @Test
    void decodeTaskData_deadlineTaskString_returnDeadlineInstance() {
        String deadlineTestData = "D,Test Deadline Task,Y,01/01/2022";
        Task task = Task.decodeTaskData(deadlineTestData);
        assertTrue(task instanceof Deadline);
    }
}
