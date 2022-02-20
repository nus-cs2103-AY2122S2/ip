package duke.command;

import java.time.LocalDateTime;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.Storage;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * A class that adds event object into a task list.
 */
public class AddEventCommand extends Command {
    private static final boolean IS_EXIT = false;
    private final String title;
    private final LocalDateTime eventAt;

    /**
     * Creates a AddEventCommand instance with a title and event date.
     *
     * @param title The title of the event.
     * @param eventAt The date of the event.
     */
    public AddEventCommand(String title, LocalDateTime eventAt) {
        super(IS_EXIT);
        this.title = title;
        this.eventAt = eventAt;
    }

    /**
     * Adds the event object to into the task list and show the execution message on the GUI.
     *
     * @param tasks The current task list.
     * @param storage The current storage of Duke.
     * @return The string of the GUI message.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            Task task = new Event(title, eventAt);
            tasks.addTask(task);
            storage.saveAllTasks(tasks);
            return TextUi.showExecutionMessage(Messages.MESSAGE_ADD_EVENT, task.toString(), tasks.getSize());
        } catch (DukeException e) {
            return TextUi.showError(e.getMessage());
        }
    }
}
