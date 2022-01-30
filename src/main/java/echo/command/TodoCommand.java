package echo.command;

import java.io.IOException;

import echo.storage.Storage;
import echo.task.TaskList;
import echo.task.TodoTask;
import echo.ui.Ui;
import echo.utils.EchoException;

/**
 * This class inherits from the Command class and encapsulates the todo command.
 */
public class TodoCommand extends Command {

    /** String that represents the todo command. */
    public static final String COMMAND = "todo";

    /** Description of todo command. */
    private final String DESCRIPTION;

    /**
     * Constructor for TodoCommand.
     *
     * @param description Description of todo command.
     */
    public TodoCommand(String description) {
        this.DESCRIPTION = description;
    }

    /**
     * Executes command.
     *
     * @param tasks TaskList containing list of tasks.
     * @param ui Ui that deals with user interactions.
     * @param storage Storage deals with loading tasks from the file and saving tasks in the file.
     *
     * @return String message representing command successful execution.
     *
     * @throws EchoException If input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EchoException {
        try {
            tasks.add(new TodoTask(DESCRIPTION));
            storage.save(tasks);
            return ui.showAdd(tasks.taskStatus(tasks.size() - 1), tasks.size());
        } catch (IOException e) {
            throw new EchoException("Unable to access folder: " + storage.filePath());
        }
    }
}
