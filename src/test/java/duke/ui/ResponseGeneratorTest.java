package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.command.TaskList;
import duke.task.Deadline;
import duke.task.Todo;

public class ResponseGeneratorTest {
    @Test
    public void testGetAddTaskMessage() {
        assertEquals("added o.O:\n  [T][ ] this week iP\nNow there are 3 tasks on the list x)",
                new ResponseGenerator().getAddTaskMessage(new Todo("this week iP"), 3));
    }

    @Test
    public void printItems_emptyList_success() {
        assertEquals("There are no tasks on your list :O", new ResponseGenerator().printItems(new ArrayList<>()));
    }

    @Test
    public void printItems_nonEmptyList_success() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Deadline("this week iP", "2022-01-27"));
        taskList.addTask(new Todo("write junit tests"));
        assertEquals("Here are the tasks on your list :O\n"
                + "1. [D][ ] this week iP (by: Jan 27 2022)\n"
                + "2. [T][ ] write junit tests", new ResponseGenerator().printItems(taskList.getItems()));
    }
}
