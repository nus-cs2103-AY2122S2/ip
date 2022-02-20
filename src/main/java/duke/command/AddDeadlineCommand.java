package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that adds deadline object into a task list.
 */
public class AddDeadlineCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;
    private final LocalDateTime dueBy;

    /**
     *  Creates a AddDeadlineCommand instance with a title and due date.
     *
     * @param title The title of a task with deadline.
     * @param dueBy The due date of the task.
     */
    public AddDeadlineCommand(String title, LocalDateTime dueBy) {
        super(IS_EXIT);
        this.title = title;
        this.dueBy = dueBy;
    }

    /**
     * Adds the deadline object to into the task list and show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = new Deadline(title, dueBy);
            tasks.addTask(task);
            storage.saveAllTasks(tasks);
            return TextUi.showExecutionMessage(Messages.MESSAGE_ADD_DEADLINE, task.toString(), tasks.getSize());
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
