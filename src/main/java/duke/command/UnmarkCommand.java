package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents unmark command
 */
public class UnmarkCommand extends Command {
    private int number;

    public UnmarkCommand(int number) {
        this.number = number - 1;
    }

    /**
     * Marks task on the list as undone
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (number >= 0 && number < tasks.getTasks().size()) {
            tasks.unmarkTask(number);
            ui.showUnmarkTask(tasks.getTasks().get(number));
            storage.save(tasks.getTasks());
        } else {
            throw new DukeException("Invalid number");
        }
    }
}
