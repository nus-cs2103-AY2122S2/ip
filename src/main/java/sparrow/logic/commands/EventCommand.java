package sparrow.logic.commands;

import sparrow.logic.task.Event;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;


/**
 * Represents the command to add an event.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
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
