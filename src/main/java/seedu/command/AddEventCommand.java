package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.task.Event;
import seedu.duke.TaskList;

/**
 * Adds task to list based on user input.
 */
public class AddEventCommand extends Command {
    private Event task;

    public AddEventCommand(String taskType, String taskDetails) {
        assert taskDetails != null : "AddEventCommand->AddEventCommand: Task details cannot be null.";
        assert taskType != null : "AddEventCommand->AddEventCommand: Task type cannot be null.";
        assert taskType.equalsIgnoreCase("event")
                : "AddDeadlineCommand->AddDeadlineCommand: Task type must be an event task.";

        String description = taskDetails.split(" - at: ")[0];
        String dateTime = taskDetails.split(" - at: ")[1];
        this.task = new Event(description, dateTime);
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
        assert tasksList != null : "AddEventCommand->run: Tasks list cannot be null.";
        assert storage != null : "AddEventCommand->run: Storage cannot be null.";

        if (!tasksList.findEventClashes(task.getDate())) {
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
        } else {
            return "Sorry, the event you are trying to add \n clashes with an existing event task :(";
        }
    }
}
