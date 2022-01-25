package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;

/**
 * Represents commands which mark a task in the task list. A MarkCommand
 * object corresponds a valid Ekud command, which can then be executed.
 */
public class MarkCommand implements Command {

    private String fullCommand;
    private String[] splicedFullCommand;
    private int position;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
        this.splicedFullCommand = this.fullCommand.split(" ", 2);
        this.position = Integer.parseInt(splicedFullCommand[1]);
    }

    /**
     * Executes a valid Ekud command that marks a task in the task list.
     * The position of which the task is supplied by the user, of which
     * if the postion is < 1 or more than the number of tasks in the task list,
     * a DukeException is thrown.
     * @param tasks Task object.
     * @param storage Storage object.
     * @param ui Ui object.
     * @throws DukeException If position < 1 or more than number of tasks in task list.
     * @throws IOException If directory or file cannot be found.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException, IOException {
        if (position < 1 || position > tasks.getTaskSize()) {
            throw new DukeException("Task do not exist!");
        } else {
            Task task = tasks.getTask(position);
            if (task.isDone == true) {
                throw new DukeException("Task is already marked as done!");
            } else {
                task.mark();
                storage.setInFile(position, task.taskDescriptionForFile());
                ui.showMarkMessage(task);
            }
        }
    }

    /**
     * Returns a boolean value that tells the programme to exit.
     * @return Boolean value false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
