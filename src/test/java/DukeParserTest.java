import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.command.Parser;
import duke.error.DukeException;
import duke.task.TaskList;




public class DukeParserTest {
    @Test
    public void validateCommand() {
        TaskList tl = new TaskList();

        //LIST
        assertEquals(-1, checkErrorMessage("list /on", "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("list /after", "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("list /before", "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("list /on adsf54fasd54",
                "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("list /before adsf54fasd54",
                "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("list /after adsf54fasd54",
                "Provide the date in the format dd-mm-yyyy!", tl));

        //MARK, UNMARK, DELETE
        assertEquals(-1, checkErrorMessage("mark", "Task ID must be provided!", tl));
        assertEquals(-1, checkErrorMessage("unmark", "Task ID must be provided!", tl));
        assertEquals(-1, checkErrorMessage("delete", "Task ID must be provided!", tl));
        assertEquals(-1, checkErrorMessage("mark ASDF", "Task ID must be an integer!", tl));
        assertEquals(-1, checkErrorMessage("unmark ASDF", "Task ID must be an integer!", tl));
        assertEquals(-1, checkErrorMessage("delete ASDF", "Task ID must be an integer!", tl));
        assertEquals(-1, checkErrorMessage("mark 1", "Task List is empty!", tl));
        assertEquals(-1, checkErrorMessage("unmark 1", "Task List is empty!", tl));
        assertEquals(-1, checkErrorMessage("delete 1", "Task List is empty!", tl));
        tl.addTask("111", false, null, 0);
        assertEquals(-1, checkErrorMessage("mark 2", "Task ID out of range!", tl));
        assertEquals(-1, checkErrorMessage("unmark 2", "Task ID out of range!", tl));
        assertEquals(-1, checkErrorMessage("delete 2", "Task ID out of range!", tl));

        //TASKS
        assertEquals(-1, checkErrorMessage("todo", "Task Name must be provided!", tl));
        assertEquals(-1, checkErrorMessage("event", "Task Name must be provided!", tl));
        assertEquals(-1, checkErrorMessage("deadline", "Task Name must be provided!", tl));
        assertEquals(-1, checkErrorMessage("deadline 222",
                "/by flag not detected.\nPlease specify date using /by!", tl));
        assertEquals(-1, checkErrorMessage("event 222", "/at flag not detected.\nPlease specify date using /at!", tl));
        assertEquals(-1, checkErrorMessage("deadline 222 /by", "Please specify deadline date after /by!", tl));
        assertEquals(-1, checkErrorMessage("event 222 /at", "Please specify deadline date after /at!", tl));
        assertEquals(-1, checkErrorMessage("deadline 222 /by aaa", "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("event 222 /at aaa", "Provide the date in the format dd-mm-yyyy!", tl));
        assertEquals(-1, checkErrorMessage("deadline /by 11/11/1998", "Task Name must be provided!", tl));
        assertEquals(-1, checkErrorMessage("event /at 11/11/1998", "Task Name must be provided!", tl));
    }

    public int checkErrorMessage(String message, String expectedMessage, TaskList tl) {
        String[] args = message.split("\\s+");
        String action = args[0];
        try {
            Parser.validate(message, action, args, tl);
        } catch (DukeException de) {
            assertEquals(expectedMessage, de.getMessage());
            return -1;
        }
        return 0;
    }

}
