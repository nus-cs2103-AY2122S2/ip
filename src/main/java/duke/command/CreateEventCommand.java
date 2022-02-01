package duke.command;

import duke.common.DukeException;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;

/**
 * Responsible for the functionality needed when creating an Event task.
 */
public class CreateEventCommand extends Command {

    /** Event task created. */
    private Event event;

    /**
     * Constructor to create CreateEventCommand.
     *
     * @param description string describing the task.
     * @param start the start date-time of the event.
     * @param end the end date-time of the event.
     */
    public CreateEventCommand(String description, LocalDateTime start,
                              LocalDateTime end) {
        super(CommandType.CREATE_EVENT);
        event = new Event(description, start, end);
    }

    /**
     * Adds the Event task to task list, saves task list to file and
     * print out the response message via user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chat bot.
     * @param storage storage used by chat bot.
     * @throw DukeException if there is File I/O exception when saving file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.add(event);
        storage.saveToFile(taskList);
        ui.showMessage("Got it! I've added this Event task:\n " + event + "\n"
                + taskList.sizeDescription());
    }
}
