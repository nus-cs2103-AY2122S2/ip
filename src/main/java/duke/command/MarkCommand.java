package duke.command;

import duke.Storage;
import duke.TaskMaster;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Deals with marking the completion status of a Task
 */
public class MarkCommand extends Command {

    private boolean mark;
    private int id;

    /**
     * Constructor
     * @param id Task that is to be marked.
     * @param mark boolean on whether to mark or unmark this task's completion status
     * @throws DukeException
     */
    public MarkCommand(String id, boolean mark) throws DukeException {
        try {
            this.id = Integer.parseInt(id);
            this.mark = mark;
        } catch (NumberFormatException e) {
            throw new DukeException("That is no item in this list with that id");
        }

    }

    /**
     * Retrieves the given task from the list of tasks using the id, and then marks its completion status.
     * Notifies the user using the Ui, and then saves the updated list to file.
     * @param tasks holds all the tasks that the user has recorded down.
     * @param ui used to notify the user of task completion.
     * @param storage saves the tasks to file if there were any edits to it.
     * @throws DukeException
     */
    @Override
    public String execute(TaskMaster tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task taskToMark = tasks.getTasks().get(id - 1);
            if (this.mark) {
                taskToMark.mark();
                storage.saveToFile(tasks.getTasks());
                return ui.notifyMarkedTaskMessage(taskToMark, true);
            } else {
                taskToMark.unmark();
                storage.saveToFile(tasks.getTasks());
                return ui.notifyMarkedTaskMessage(taskToMark, false);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("No such task exists! Are you sure about that task number?");
        }

    }
}
