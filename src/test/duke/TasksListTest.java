package duke;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TasksListTest {

    @Test
    void addTask() {
        try {
            TasksList tasksList = new TasksList();
            for (int i = 0; i < 10; i++) {
                tasksList.addTask(Arrays.asList("todo", "Coding"));
                tasksList.addTask(Arrays.asList("deadline", "Coding", "/by", "2022-12-10"));
                tasksList.addTask(Arrays.asList("event", "Coding", "/at", "my house"));
            }
            assertEquals(30, tasksList.getSize());
        }
        catch(DukeException e) {
            fail();
        }
    }

    @Test
    void deleteTask() {
        try {
            TasksList tasksList = new TasksList();
            for (int i = 0; i < 10; i++) {
                tasksList.addTask(Arrays.asList("todo", "Coding"));
            }
            for (int i = 0; i < 10; i++) {
                tasksList.deleteTask(1);
            }
            assertEquals(0, tasksList.getSize());
        }
        catch(DukeException e) {
            fail();
        }
    }
}