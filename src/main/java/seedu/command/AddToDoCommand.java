package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;
import seedu.task.ToDo;

import java.io.IOException;

/**
 * Adds task to list based on user input.
 */
public class AddToDoCommand extends Command {
    private ToDo task;

    public AddToDoCommand(String taskDetails) {
        assert taskDetails != null : "AddToDoCommand->AddToDoCommand: Task details cannot be null.";
        assert taskDetails.length() > 0 : "AddToDoCommand->AddToDoCommand: Task details cannot be empty.";

        this.task = new ToDo(taskDetails);
    }

    /**
     * Executes the add command to add a new task to the task list and write
     * the modified task list back to the storage.
     *
     * @param tasksList Current list of tasks
     * @param storage Storage Object to write tasks
     * @return Output message for GUI.
     */
    public String run(TaskList tasksList, Storage storage) throws DukeException {
        assert tasksList != null : "AddToDoCommand->run: Tasks list cannot be null.";
        assert storage != null : "AddToDoCommand->run: Storage cannot be null.";

        tasksList.add(task);
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
        }

        String result = "Got it. I've added this task:\n";
        result += task.toString();
        result += "\nNow you have ";
        result += tasksList.size();
        result += " tasks in the list.";
        return result;
    }
}
