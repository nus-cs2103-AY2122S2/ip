package duke.commands;

import duke.data.task.Deadline;
import duke.data.task.Task;
import duke.data.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

import java.time.LocalDate;

/**
 * Add a deadline to the tasklist.
 */
public class AddDeadlineCommand extends Command {
    
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the task list. "
            + "Parameters: DESCRIPTION /by TIME\n"
            + "Example: " + COMMAND_WORD
            + " deadline Do homework /by 2022-02-01";

    private final Task toAdd;

    public AddDeadlineCommand(String description, LocalDate time) {
        this.toAdd = new Deadline(description, time);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(toAdd);
        ui.showAddTask(toAdd, tasks.getSize());
    }
}
