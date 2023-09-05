package duke.command;

import duke.main.Storage;
import duke.main.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.Task;

/**
 * Represents delete command
 */
public class DeleteCommand extends Command{
    private int number;

    public DeleteCommand(int number) {
        this.number = number - 1;
    }

    /**
     * Deletes task from the list
     *
     * @param tasks contains list of tasks
     * @param ui interact with user
     * @param storage save tasks to file
     * @throws DukeException if I0Exception occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (number >= 0 && number < tasks.getTasks().size()) {
            Task task = tasks.getTasks().get(number);
            tasks.deleteTask(number);
            ui.showDeleteTask(task, tasks.getTasks());
            storage.save(tasks.getTasks());
        } else {
            throw new DukeException("Invalid number");
        }
    }
}
