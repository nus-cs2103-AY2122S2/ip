package echo.command;

import java.io.IOException;
import java.time.LocalDateTime;

import echo.main.EchoException;
import echo.storage.Storage;
import echo.task.DeadlineTask;
import echo.task.TaskList;
import echo.ui.Ui;

/**
 * This class inherits from the Command class and encapsulates the deadline command.
 */
public class DeadlineCommand extends Command {

    /** String that represents the deadline command. */
    public static final String COMMAND = "deadline";

    /** Description of deadline command. */
    private final String DESCRIPTION;

    /** Date and time of deadline command. */
    private final LocalDateTime DATE_TIME;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param description Description of deadline command.
     * @param dateTime Date and time of deadline command.
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.DESCRIPTION = description;
        this.DATE_TIME = dateTime;
    }

    /**
     * Execute command.
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
            tasks.add(new DeadlineTask(DESCRIPTION, DATE_TIME));
            ui.showAdd(tasks.taskStatus(tasks.size() - 1), tasks.size());
            storage.save(tasks);
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
