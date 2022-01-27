package echo.command;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.ui.Ui;
import echo.main.EchoException;

import echo.task.EventTask;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * This class inherits from the Command class and encapsulates the event command.
 */
public class EventCommand extends Command {

    /** String that represents the event command. */
    public static final String COMMAND = "event";

    /** Description of event command. */
    private final String DESCRIPTION;

    /** Date and time of event command. */
    private final LocalDateTime DATE_TIME;

    /**
     * Constructor for EventCommand.
     *
     * @param description Description of event command.
     * @param dateTime Date and time of event command.
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        this.DESCRIPTION = description;
        this.DATE_TIME = dateTime;
    }

    /**
     * Executes command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     *
     * @throws EchoException If input is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            tasks.add(new EventTask(DESCRIPTION, DATE_TIME));
            ui.showAdd(tasks.taskStatus(tasks.size() - 1), tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
