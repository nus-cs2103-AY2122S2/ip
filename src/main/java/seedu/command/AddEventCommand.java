package seedu.command;

import java.io.IOException;

import seedu.duke.Storage;
import seedu.exception.DukeException;
import seedu.task.Event;
import seedu.duke.TaskList;

/**
 * Adds task of type Event to task list based on details provided by user input.
 * Checks for any event clashes before adding event task to task list.
 */
public class AddEventCommand extends Command {
    private Event eventTask;

    public AddEventCommand(String eventDetails) throws DukeException{
        assert eventDetails != null : "AddEventCommand->AddEventCommand: Event details cannot be null.";
        assert eventDetails.length() > 0 : "AddEventCommand->AddEventCommand: Event details cannot be empty.";

        try {
            String description = eventDetails.split(" /at ")[0];
            String dateTime = eventDetails.split(" /at ")[1];
            this.eventTask = new Event(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Sorry your event details is in the wrong format, please use: " +
                    "\n [event description] /at [DD-MM-YYYY]");
        }
    }

    /**
     * Executes the add command to add a new Event task to the task list and write
     * the modified task list back to the storage.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage Object to write tasks back to.
     * @return Display message that task has been added to the list successfully.
     * @throws DukeException  If task list cannot be written back to storage location.
     */
    public String run(TaskList taskList, Storage storage) throws DukeException{
        assert taskList != null : "AddEventCommand->run: Task list cannot be null.";
        assert storage != null : "AddEventCommand->run: Storage cannot be null.";

        if (!taskList.findEventClashes(eventTask.getDate())) {
            taskList.add(eventTask);
            try {
                storage.write(taskList.getTaskList());
            } catch (IOException e) {
                throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
            }

            String result = "Got it. I've added this task:\n" + eventTask.toString()
                    + "\nNow you have " + taskList.size() + " tasks in the list.";
            return result;
        } else {
            return "Sorry, the event you are trying to add \n clashes with an existing event task :(";
        }
    }
}
