package duke.command;

import java.util.Comparator;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class SortCommand extends Command {
    private static final boolean IS_EXIT = false;

    public SortCommand() {
        super(IS_EXIT);
    }

    /**
     * Sorts all Task objects in the TaskList based on priority followed by deadline
     *
     * @param tasks The TaskList instance containing the Task object to be marked or unmarked.
     * @param ui    The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        tasks.sort(Comparator.comparing(Task::getPriority));
        new ListCommand().execute(tasks, ui, storage);
    }
}
