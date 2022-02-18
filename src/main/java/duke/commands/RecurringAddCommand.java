package duke.commands;

import java.io.IOException;
import java.util.ArrayList;

import duke.info.task.Calendar;
import duke.info.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class RecurringAddCommand extends Command {
    private final ArrayList<Task> recurringTasksToAdd;

    /**
     * Constructs a RecurringAddCommand with the specified list of
     * tasks to add
     * @param recurringTasksToAdd - list of tasks to be added
     */
    public RecurringAddCommand(ArrayList<Task> recurringTasksToAdd) {
        this.recurringTasksToAdd = recurringTasksToAdd;
    }

    /**
     * Adds all the tasks specified in the recurringTasksToAdd ArrayList to the specified calendar.
     * Afterwards, diisplays a success message showing that the task has been successfully added
     * using the ui handler, and save the new version of the calendar to storage
     * @param calendar - the calendar used in the program
     * @param ui - the ui handler for the program
     * @param storage - the storage handler for the program
     * @return success message if complete or error message if execution failed
     */
    @Override
    public String execute(Calendar calendar, Ui ui, Storage storage) {
        try {
            calendar.addAll(recurringTasksToAdd);
            storage.save(calendar);
            return ui.showAllTasksAdded(calendar.numOfEntries());
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}
