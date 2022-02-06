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
     * Signifies to the app to not terminate its current run.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Will carry out an operation on a specified task in the
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
        assert this.number > 0 : "The number supplied should not be 0 or negative";
        try {
            if (this.taskType.equals("mark")) {
                Task task = tasks.get(this.number - 1);

                if (task.isMarked()) {
                    return ui.showAlreadyMarkedMessage();
                }

                String oldMark = task.formatText();
                task.markTask();
                stg.pushToMemory("mark " + this.number);
                String replaceMark = task.formatText();
                stg.editData(oldMark, replaceMark);
                return ui.showSuccessfulMarkMessage() + "\n" + task;

            } else if (this.taskType.equals("unmark")) {
                Task task = tasks.get(this.number - 1);

                if (!task.isMarked()) {
                    return ui.showAlreadyUnmarkedMessage();
                }

                String oldMark = task.formatText();
                task.unmarkTask();
                stg.pushToMemory("unmark " + this.number);
                String replaceMark = task.formatText();
                stg.editData(oldMark, replaceMark);
                return ui.showSuccessfulUnmarkMessage() + "\n" + task;

            } else {
                Task task = tasks.get(this.number - 1);
                String oldDelete = task.formatText();
                tasks.deleteTask(number - 1);
                stg.pushToMemory("delete " + this.number + " -" + task.formatText());
                stg.editData(oldDelete, " ");

                ui.showCount(tasks);
                return ui.showSuccessfulDeleteMessage() + "\n" + task + "\n"
                        + ui.showNumberOfTasksMessage(tasks);
            }
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid number!";
        }
    }
}
