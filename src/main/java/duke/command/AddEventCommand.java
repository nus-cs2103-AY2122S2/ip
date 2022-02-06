package duke.command;

import duke.storage.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents add new event command.
 * inherit from Command.
 */
public class AddEventCommand extends Command {
    private final Event newEvent;

    /**
     * Returns an event command with new event.
     *
     * @param newEvent new event.
     */
    public AddEventCommand(Event newEvent) {
        super();
        this.newEvent = newEvent;
    }

    /**
     * Returns a deadline command with new deadline.
     *
     * @param tasks   the entire TaskList.
     * @param ui      the ui interface and messages.
     * @param storage the storage operations.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(new Event(newEvent.getTask(), newEvent.getDate()));
        storage.saveTaskList(tasks);
        ui.showMessage("Got it. I've added this task: \n        "
                + tasks.getByIndex(tasks.getSize() - 1) + "\n    Now you have "
                + tasks.getSize() + " tasks in the list.");

    }
}
