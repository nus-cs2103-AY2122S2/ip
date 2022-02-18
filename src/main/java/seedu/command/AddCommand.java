package seedu.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import seedu.duke.Storage;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.duke.TaskList;
import seedu.task.ToDo;

/**
 * Adds task to list based on user input.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(String taskType, String taskDetails) {
        assert taskDetails != null : "AddCommand->AddCommand: Task details cannot be null.";
        assert taskType != null : "AddCommand->AddCommand: Task type cannot be null.";

        String description;
        String dateTime;
        if (taskType.equalsIgnoreCase("deadline")) {
            description = taskDetails.split(" - by: ")[0];
            dateTime = taskDetails.split(" - by: ")[1];
            this.task = new Deadline(description, dateTime);
        } else if (taskType.equalsIgnoreCase("event")) {
            description = taskDetails.split(" - at: ")[0];
            dateTime = taskDetails.split(" - at: ")[1];
            this.task = new Event(description, dateTime);
        } else if (taskType.equalsIgnoreCase("todo")) {
            description = taskDetails;
            this.task = new ToDo(description);
        }
    }

    /**
     * Executes the add command to add a new task to the task list and write
     * the modified task list back to the storage.
     *
     * @param tasksList Current list of tasks
     * @param storage Storage Object to write tasks
     * @return Output message for GUI.
     */
    public String run(TaskList tasksList, Storage storage) {
        assert tasksList != null : "AddCommand->run: Tasks list cannot be null.";
        assert storage != null : "AddCommand->run: Storage cannot be null.";

        tasksList.add(task);
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        String result = "Got it. I've added this task:\n";
        result += task.toString();
        result += "\nNow you have ";
        result += tasksList.size();
        result += " tasks in the list.";
        return result;
    }
}
