package seedu.command;

import java.io.IOException;
import java.time.LocalDate;

import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Deadline;
import seedu.duke.Event;
import seedu.duke.Task;
import seedu.duke.TaskList;
import seedu.duke.ToDo;


/**
 * Adds task to list based on user input.
 */
public class AddCommand extends Command {
    /**
     * Executes the add command.
     *
     * @return Output message for GUI.
     */
    public static String run(Task t, TaskList tasksList, Storage storage) throws DukeException, IOException {
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException exception) {
            return "Something went wrong: " + exception.getMessage();
        }
        String result = "Got it. I've added this task:\n";
        result += t.toString();
        result += "\nNow you have ";
        result += tasksList.size();
        result += " tasks in the list.";
        return result;
    }
}