package luca.command;

import java.time.LocalDateTime;

import luca.common.DukeException;
import luca.storage.Storage;
import luca.task.Event;
import luca.task.TaskList;

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
     * returns response message.
     *
     * @param taskList list of tasks.
     * @param storage storage used by chat bot.
     * @return response string.
     * @throw DukeException if there is File I/O exception when saving file.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.add(event);
        storage.saveToFile(taskList);
        return "Got it! I've added this Event task:\n " + event + "\n"
                + taskList.sizeDescription();
    }
}
