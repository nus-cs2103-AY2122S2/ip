package duke.commands;

import duke.info.task.Calendar;
import duke.info.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.io.IOException;

public abstract class AddCommand extends Command {

    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(Calendar calendar, Ui ui, Storage storage) {
        try {
            calendar.add(taskToAdd);
            ui.showTaskAdded(taskToAdd, calendar.numOfEntries());
            storage.save(calendar);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
