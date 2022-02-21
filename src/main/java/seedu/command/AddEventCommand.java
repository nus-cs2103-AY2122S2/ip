package seedu.command;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.exception.DukeException;
import seedu.task.Event;

/**
 * Adds task of type Event to task list based on details provided by user input.
 * Checks for any event clashes before adding event task to task list.
 */
public class AddEventCommand extends Command {
    private final Event eventTask;

    public AddEventCommand(String eventDetails) throws DukeException {
        assert eventDetails != null : "AddEventCommand->AddEventCommand: Event details cannot be null.";
        assert eventDetails.length() > 0 : "AddEventCommand->AddEventCommand: Event details cannot be empty.";

        try {
            String description = eventDetails.split(" /at ")[0];
            String dateTime = eventDetails.split(" /at ")[1];
            this.eventTask = new Event(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Sorry your event details is in the wrong format, please use: "
                    + "\n [event description] /at [DD-MM-YYYY]");
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry your event date is in the wrong format, please use: "
                    + "\n [DD-MM-YYYY]");
        }
    }

    /**
     * Executes the add command to add a new Event task to the task list and write
     * the modified task list back to the storage.
     *
     * @param taskList Current list of tasks.
     * @param storage Storage object to write tasks back to.
     * @return Display message if the task has been added to the list successfully or if there are event clashes.
     * @throws DukeException  If task list cannot be written back to storage location.
     */
    @Override
    public String run(TaskList taskList, Storage storage) throws DukeException {
        assert taskList != null : "AddEventCommand->run: Task list cannot be null.";
        assert storage != null : "AddEventCommand->run: Storage cannot be null.";

        if (!taskList.findEventClashes(eventTask.getDate())) {
            taskList.add(eventTask);
            try {
                storage.write(taskList.getTaskList());
            } catch (IOException e) {
                throw new DukeException("Something went wrong when I tried to write your task list back to storage :(");
            }

            return "Got it. I've added this task:\n" + eventTask
                    + "\nNow you have " + taskList.size() + " tasks in the list.";
        } else {
            return "Sorry, the event you are trying to add \n clashes with an existing event task :(";
        }
    }
}
