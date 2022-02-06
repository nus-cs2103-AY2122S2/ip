package jeff.command;

import jeff.main.JeffException;

import jeff.storage.Storage;

import jeff.task.Deadline;
import jeff.task.Task;
import jeff.task.TaskList;

import jeff.ui.Ui;

/**
 * DeadlineCommand class is a Command that contains instructions
 * to run when user wants to add a new Deadline task.
 */
public class DeadlineCommand extends Command {

    private String description;
    private String dateInfo;

    /**
     * Constructor of DeadlineCommand.
     *
     * @param description Name of the task.
     * @param dateInfo End date of the task.
     */
    public DeadlineCommand(String description, String dateInfo) {
        this.description = description;
        this.dateInfo = dateInfo;
    }

    /**
     * Creates a new Deadline task and store it
     * into the task list and feedback to the user.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     * @throws JeffException When no available format is available
     *                       to parse dateInfo or file cannot be saved.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        Task currTask = new Deadline(description, dateInfo);
        tasks.add(currTask);
        ui.showAdded(currTask.toString(), tasks.size());
        storage.save(tasks);
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return false to keep running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
