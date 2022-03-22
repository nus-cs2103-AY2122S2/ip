package duke.commands;

import java.time.LocalDate;

import duke.data.TasksEditor;
import duke.data.task.Deadline;
import duke.data.task.Task;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Adds a deadline to the tasklist.
 */
public class AddDeadlineCommand extends Command {
    
    public static final String COMMAND_WORD = "deadline";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline to the task list.\n\t"
            + "Parameters: DESCRIPTION /by TIME\n\t"
            + "Example: " + COMMAND_WORD
            + " Do homework /by 2022-02-01";

    private final Task toAdd;

    public AddDeadlineCommand(String description, LocalDate time) {
        this.toAdd = new Deadline(description, time);
    }

    @Override
    public String execute(TasksEditor tasksEditor, Ui ui, Storage storage) {
        tasksEditor.add(toAdd);
        return ui.showAddTask(toAdd, tasksEditor.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddDeadlineCommand)) {
            return false;
        }

        AddDeadlineCommand command = (AddDeadlineCommand) obj;
        return this.toAdd.equals(command.toAdd);
    }
}
