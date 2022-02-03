package duke.commands;

import duke.DukeException;
import duke.data.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.data.TaskList;
import duke.data.task.Event;

import java.time.LocalDate;

/**
 * Add an event to the tasklist.
 */
public class AddEventCommand extends Command {
    
    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to the task list. "
            + "Parameters: DESCRIPTION /at TIME\n"
            + "Example: " + COMMAND_WORD
            + " event Lecture /at 2022-02-01";

    private final Task toAdd;

    public AddEventCommand(String description, LocalDate time) {
        this.toAdd = new Event(description, time);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toAdd);
        ui.showAddTask(toAdd, tasks.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddEventCommand)) {
            return false;
        }

        AddEventCommand command = (AddEventCommand) obj;
        return this.toAdd.equals(command.toAdd);
    }
}
