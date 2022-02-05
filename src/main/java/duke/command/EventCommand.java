package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.ui.Ui;

/**
 * Represents the command to add an event.
 */
public class EventCommand extends Command {
    private final Event event;

    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    /**
     * Executes the command.
     *
     * @param storage  Storage of Duke.
     * @param tasks Task list of Duke.
     * @param ui User interface of Duke.
     * @throws DukeException if there is a problem updating the storage or user interface.
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(event);
        return ui.addTask(event) + ui.countTasks(tasks);
    }

    /**
     * Indicates whether the program should stop after this command.
     *
     * @return Boolean indicating whether the program should stop after this command.
     */
    public boolean isExit() {
        return false;
    }
}
