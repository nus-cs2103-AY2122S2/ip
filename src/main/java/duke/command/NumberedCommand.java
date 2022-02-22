package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Task;
import java.io.IOException;

/**
 * Represents a Command object that is carry out an action
 * on a specified task in the tasklist.
 */
public class NumberedCommand extends Command {

    private int number;
    private String taskType;
    private String textInput;

    /**
     * Constructor for the NumberedCommand object.
     *
     * @param val The index of the task to operate on.
     * @param type The type of tasks to be added,
     * (Todo, Deadline, Event).
     * @param input The user's input containing the information
     * of the task to be added.
     */
    public NumberedCommand(int val, String type, String input) {
        this.number = val;
        this.taskType = type;
        this.textInput = input;
    }

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Performs an operation on a specified task in the
     * tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return Message indicating that the numbered task has been
     * operated on.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws IOException {
        String resultMessage = "";
        try {
            assert this.number > 0 : "The number supplied should not be 0 or negative";
            Task opTask = tasks.get(this.number - 1);
            if (this.taskType.equals("mark")) {
                resultMessage = markNumberedTask(stg, ui, tasks, opTask);
            } else if (this.taskType.equals("unmark")) {
                resultMessage = unmarkNumberedTask(stg, ui, tasks, opTask);
            } else {
                resultMessage = deleteNumberedTask(stg, ui, tasks, opTask);
            }
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid number!";
        }
        return resultMessage;
    }

    /**
     * Performs a mark operation on a specified task in the
     * tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @param opTask The task that will be marked.
     * @return Message indicating that the numbered task has been
     * marked.
     * @throws IOException If an I/O error occurs.
     */
    public String markNumberedTask(Storage stg, Ui ui, TaskList tasks, Task opTask)
            throws IOException {
        if (opTask.isMarked()) {
            return ui.showAlreadyMarkedMessage();
        }
        String oldMark = opTask.formatText();
        opTask.markTask();
        stg.pushToMemory("mark " + this.number);
        String replaceMark = opTask.formatText();
        stg.editData(oldMark, replaceMark);
        return ui.showSuccessfulMarkMessage() + "\n" + opTask;
    }

    /**
     * Performs an unmark operation on a specified task in the
     * tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @param opTask The task that will be unmarked.
     * @return Message indicating that the numbered task has been
     * unmarked.
     * @throws IOException If an I/O error occurs.
     */
    public String unmarkNumberedTask(Storage stg, Ui ui, TaskList tasks, Task opTask)
            throws IOException {
        if (!opTask.isMarked()) {
            return ui.showAlreadyUnmarkedMessage();
        }
        String oldMark = opTask.formatText();
        opTask.unmarkTask();
        stg.pushToMemory("unmark " + this.number);
        String replaceMark = opTask.formatText();
        stg.editData(oldMark, replaceMark);
        return ui.showSuccessfulUnmarkMessage() + "\n" + opTask;
    }

    /**
     * Performs a deletion operation on a specified task in the
     * tasklist.
     *
     * @param stg The storage object to use file writing methods.
     * @param ui The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @param opTask The task that will be deleted.
     * @return Message indicating that the numbered task has been
     * deleted.
     * @throws IOException If an I/O error occurs.
     */
    public String deleteNumberedTask(Storage stg, Ui ui, TaskList tasks, Task opTask)
            throws IOException {
        String oldDelete = opTask.formatText();
        tasks.deleteTask(number - 1);
        stg.pushToMemory("delete " + this.number + " -" + opTask.formatText());
        stg.editData(oldDelete, " ");

        ui.showCount(tasks);
        return ui.showSuccessfulDeleteMessage() + "\n" + opTask + "\n"
                + ui.showNumberOfTasksMessage(tasks);
    }
}
