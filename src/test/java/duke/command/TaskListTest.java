package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidActionException;
import duke.task.Todo;
import duke.ui.Ui;

public class TaskListTest {
    @Test
    public void markItemDone_completedItem_exceptionThrown() {
        TaskList tl = new TaskList();
        tl.addTask(new Todo("this week iP", true));
        try {
            tl.markItemDone(0);
            assertEquals("[T][X] this week iP", tl.getLast().toString());
            fail();
        } catch (InvalidActionException e) {
            assertEquals("Task already done!", e.getMessage());
        }
    }

    @Test
    public void markItemUndone_completedItem_success() {
        TaskList tl = new TaskList();
        tl.addTask(new Todo("this week iP", true));
        try {
            tl.markItemUndone(0);
            assertEquals("[T][ ] this week iP", tl.getLast().toString());
        } catch (InvalidActionException e) {
            fail();
        }
    }

    @Test
    public void testDeleteItem() {
        TaskList tl = new TaskList();
        Ui ui = new Ui();
        tl.addTask(new Todo("this week iP"));
        assertEquals("Here are the tasks on your list :O\n1. [T][ ] this week iP",
                ui.printItems(tl.getItems()));
        assertEquals("deleted this item O_O:\n  [T][ ] this week iP\nNow there are 0 tasks on the list x)",
                tl.deleteItem(0));
        assertEquals("There are no tasks on your list :O", ui.printItems(tl.getItems()));
    }
}
