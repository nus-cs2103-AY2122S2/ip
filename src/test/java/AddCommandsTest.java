import Taskmaster.Commands.AddCommands;
import Taskmaster.Task.TodoTask;
import Taskmaster.Task.DeadlineTask;
import Taskmaster.Task.EventTask;
import Taskmaster.util.ParseFiles;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import Taskmaster.util.TaskList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import taskmaster.util.TaskList;

public class AddCommandsTest {
    private final TaskList TASKLIST = new TaskList();

    private void setupParameters() {
        if (TASKLIST.currentSize != 0) {
            for (int i = 0; i < TASKLIST.currentSize; i++)
                TASKLIST.delete(i);
        }
    }

    @Test
    @DisplayName("Test TodoTask creation")
    public void testAddTodoTask() {
        setupParameters();
        int initialSize = TASKLIST.currentSize;
        AddCommands command = new AddCommands("todo me", TASKLIST);
        command.execute();
        assertTrue(initialSize != TASKLIST.currentSize);
        assertTrue(TASKLIST.get(TASKLIST.currentSize - 1) instanceof TodoTask);
    }

    @Test
    @DisplayName("Test DeadlineTask creation")
    public void testAddDeadlineTask() {
        setupParameters();
        int initialSize = TASKLIST.currentSize;
        AddCommands command = new AddCommands("deadline eat some pizza /by 10/10/2010 1000", TASKLIST);
        command.execute();
        assertTrue(initialSize != TASKLIST.currentSize);
        assertTrue(TASKLIST.get(TASKLIST.currentSize - 1) instanceof DeadlineTask);
    }


    @Test
    @DisplayName("Test EventTask creation")
    public void testAddEventTask() {
        setupParameters();
        int initialSize = TASKLIST.currentSize;
        AddCommands command = new AddCommands("event Spiderman No Way Home /at 01/01/2022 2359", TASKLIST);
        command.execute();
        assertTrue(initialSize != TASKLIST.currentSize);
        assertTrue(TASKLIST.get(TASKLIST.currentSize - 1) instanceof EventTask);
    }


}
