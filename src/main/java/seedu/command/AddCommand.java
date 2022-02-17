package seedu.command;

import java.io.IOException;

import seedu.exception.DukeException;
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
        String description;
        String dateTime;
        switch(taskType) {
        case "deadline":
            description = taskDetails.split(" - by: ")[0];
            dateTime = taskDetails.split(" ")[1];
            this.task = new Deadline(description, dateTime);
        case "event":
            description = taskDetails.split(" - at: ")[0];
            dateTime = taskDetails.split(" ")[1];
            this.task = new Event(description, dateTime);
        case "todo":
            description = taskDetails;
            this.task = new ToDo(description);
        }
    }

    /**
     * Executes the add command to add a new task to the task list and write
     * the modified task list back to the storage.
     *
     * @param tasksList
     * @param storage
     * @return Output message for GUI.
     */
    public String run(TaskList tasksList, Storage storage) {
        tasksList.add(task);
        try {
            storage.write(tasksList.getTaskList());
        } catch (IOException exception) {
            return "Something went wrong: " + exception.getMessage();
        }

        String result = "Got it. I've added this task:\n";
        result += task.toString();
        result += "\nNow you have ";
        result += tasksList.size();
        result += " tasks in the list.";
        return result;
    }
}
