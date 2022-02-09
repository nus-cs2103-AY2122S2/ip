package bobby.command;

import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.task.Deadline;
import bobby.task.TaskList;

/**
 * Represents a 'deadline' command
 */
public class DeadlineCommand extends Command {
    /** The name of the Deadline task */
    private String taskName;
    /** The date to tied to this task */
    private String byDate;

    /**
     * Creates a DeadLineCommand object.
     *
     * @param taskName Name of the Deadline task.
     * @param byDate Date of the Deadline task.
     */
    public DeadlineCommand(String taskName, String byDate) {
        this.taskName = taskName;
        this.byDate = byDate;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        Deadline newDeadline = new Deadline(taskName, byDate);
        tasks.addTask(newDeadline);
        storage.saveTasks(tasks.getTaskList());
        return ui.deadlineMessage(newDeadline) + "\n" + ui.printNumTasks(tasks);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are DeadlineCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeadlineCommand;
    }
}
