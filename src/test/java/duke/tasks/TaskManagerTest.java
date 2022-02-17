package duke.tasks;

import duke.exceptions.InvalidCommandExcpetion;
import duke.exceptions.InvalidParameterException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskManagerTest {

    @Test
    private static void generateAndAddTest() {
        TaskManager.reinitialise();
        String addMessage = "Great, have added the following task for you:\n%s\nNow you have %s.";
        try {
            assertEquals(TaskManager.generateAndAdd("todo borrow book"), String.format(addMessage, "[T][ ] borrow book", "1 task"));
            assertEquals(TaskManager.generateAndAdd("deadline read book /by 02-04-2022"), String.format(addMessage, "[D][ ] read book (by: Apr 02, 2022)", "2 tasks"));
            assertEquals(TaskManager.generateAndAdd("event book club meeting /at 02-04-2022"), String.format(addMessage, "[E][ ] book club meeting (at: Apr 02, 2022)", "3 tasks"));
            assertThrows(InvalidParameterException.class, () -> TaskManager.generateAndAdd("deadline return book Jan 25"));
            assertThrows(InvalidCommandExcpetion.class, () -> TaskManager.generateAndAdd("saved"));
            assertThrows(InvalidParameterException.class, () -> TaskManager.generateAndAdd("deadline"));
            assertEquals(TaskManager.generateAndAdd("deadline return book /by 02-05-2022"), String.format(addMessage, "[D][ ] return book (by: May 02, 2022)", "4 tasks"));
        } catch (Exception e) {
            System.out.println("Test Failed with an Error" + e.getMessage());
        }
    }

    @Test
    private static void markTest() {
        TaskManager.reinitialise();
        String markedMessage = "Well done! I have marked this task as done.\n%s";
        generateAndAddTest();
        assertEquals(TaskManager.mark(2), String.format(markedMessage, "[D][X] read book (by: Apr 02, 2022)"));
        assertEquals(TaskManager.mark(4), String.format(markedMessage, "[D][X] return book (by: May 02, 2022)"));
    }

    @Test
    private static void unmarkTest() {
        TaskManager.reinitialise();
        String unmarkedMessage = "No worries, I have unmarked this task, good luck!\n%s";
        generateAndAddTest();
        markTest();
        assertEquals(TaskManager.unmark(2), String.format(unmarkedMessage, "[D][ ] read book (by: Apr 02, 2022)"));
        assertEquals(TaskManager.unmark(4), String.format(unmarkedMessage, "[D][ ] return book (by: May 02, 2022)"));
    }

    @Test
    private static void deleteTest() {
        TaskManager.reinitialise();
        String deleteMessage = "Noted. I have removed the following task:\n%s\nYou now have %s";
        generateAndAddTest();
        assertEquals(TaskManager.delete(2), String.format(deleteMessage, "[D][ ] read book (by: Apr 02, 2022)", "3 tasks"));
        assertEquals(TaskManager.delete(3), String.format(deleteMessage, "[D][ ] return book (by: May 02, 2022)", "2 tasks"));
    }

    public static void test() {
        generateAndAddTest();
        markTest();
        unmarkTest();
        deleteTest();
    }
}
