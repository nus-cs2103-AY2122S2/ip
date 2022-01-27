package duke.command;

import duke.UI;
import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListCommandTest {
    @Test
    public void listCommand_emptyList_success() throws IOException, DukeException {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        new ListCommand().execute(taskList, ui);
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:\n" +
                "     ~~List is currently empty~~\n" +
                "    ____________________________________________________________\n\n", lines);
    }

    @Test
    public void listCommand_fullList_success() throws IOException, DukeException {
        UI ui = new UI();
        List<Task> taskList = new ArrayList<Task>();
        taskList.add(new TodoTask("Task 1: Todo"));
        taskList.add(new TodoTask("Task 2: Todo, Marked", true));
        taskList.add(new DeadlineTask("Task 3: Deadline w/ Date and Time", "2022-01-01", "11:11"));
        taskList.add(new DeadlineTask("Task 4: Deadline w/ Date and Time, Marked",
                true,"2022-01-01", "11:11"));
        taskList.add(new DeadlineTask("Task 5: Deadline w/ Date only", "2022-01-01"));
        taskList.add(new DeadlineTask("Task 6: Deadline w/ Date only, Marked", true, "2022-01-01"));
        taskList.add(new EventTask("Task 7: Event w/ Date and Time", "2022-01-01", "11:11"));
        taskList.add(new EventTask("Task 8: Event w/ Date and Time, marked", true, "2022-01-01", "11:11"));
        taskList.add(new EventTask("Task 9. Event w/ Date", "2022-01-01"));
        taskList.add(new EventTask("Task 10. Event w/ Date, marked", true,"2022-01-01"));
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
        new ListCommand().execute(taskList, ui);
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:\n" +
                "     1. [T][ ] Task 1: Todo\n" +
                "     2. [T][X] Task 2: Todo, Marked\n" +
                "     3. [D][ ] Task 3: Deadline w/ Date and Time (by: 2022-01-01 11:11)\n" +
                "     4. [D][X] Task 4: Deadline w/ Date and Time, Marked (by: 2022-01-01 11:11)\n" +
                "     5. [D][ ] Task 5: Deadline w/ Date only (by: 2022-01-01)\n" +
                "     6. [D][X] Task 6: Deadline w/ Date only, Marked (by: 2022-01-01)\n" +
                "     7. [E][ ] Task 7: Event w/ Date and Time (at: 2022-01-01 11:11)\n" +
                "     8. [E][X] Task 8: Event w/ Date and Time, marked (at: 2022-01-01 11:11)\n" +
                "     9. [E][ ] Task 9. Event w/ Date (at: 2022-01-01)\n" +
                "     10. [E][X] Task 10. Event w/ Date, marked (at: 2022-01-01)\n" +
                "    ____________________________________________________________\n\n", lines);
    }
}
