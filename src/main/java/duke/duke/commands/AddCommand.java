package duke.commands;

import duke.info.task.Calendar;
import duke.info.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class AddCommand extends Command {

    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        calendar.add(taskToAdd);
        ui.showTaskAdded(taskToAdd, calendar.numOfEntries());
    }
}
