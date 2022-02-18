package seedu.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.task.Deadline;

import java.io.IOException;

/**
 * Adds task to list based on user input.
 */
public class AddDeadlineCommand extends Command {
    private Deadline task;

    public AddDeadlineCommand(String taskType, String taskDetails) {
        assert taskDetails != null : "AddDeadlineCommand->AddDeadlineCommand: Task details cannot be null.";
        assert taskType != null : "AddDeadlineCommand->AddDeadlineCommand: Task type cannot be null.";
        assert taskType.equalsIgnoreCase("deadline")
                : "AddDeadlineCommand->AddDeadlineCommand: Task type must be a deadline task.";

        String description = taskDetails.split(" - by: ")[0];
        String dateTime = taskDetails.split(" - by: ")[1];
        this.task = new Deadline(description, dateTime);
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
        assert tasksList != null : "AddDeadlineCommand->run: Tasks list cannot be null.";
        assert storage != null : "AddDeadlineCommand->run: Storage cannot be null.";

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
