package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents mark command
 */
public class MarkCommand extends Command {
    private int number;

    public MarkCommand(int number) {
        this.number = number - 1;
    }

    /**
     * Marks task on the list as done
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (number >= 0 && number < tasks.getTasks().size()) {
            tasks.markTask(number);
            ui.showMarkTask(tasks.getTasks().get(number));
            storage.save(tasks.getTasks());
        } else {
            throw new DukeException("Invalid number");
        }

    }
}
