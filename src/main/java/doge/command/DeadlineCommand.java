package doge.command;

import doge.Doge;
import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DeadlineException;
import doge.exception.DogeException;
import doge.task.Task;

import java.time.LocalDateTime;

/**
 * Represents the "deadline" command. Doge bot will add a task of "deadline" type into the TaskList.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor for class DeadlineCommand.
     *
     * @param task the deadline task that is associated with this command
     */
    public DeadlineCommand (Task task) {
        super(task);
    }

    /**
     * Executes the "deadline" command. It adds a task of "deadline" type into the TaskList.
     *
     * @param tasks {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @throws DogeException if it fails to add the "deadline" task into the TaskList or user failed to provide
     * necessary details to initialise a Deadline task
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        String[] curr = this.task.getDescription().split("/");

        if (this.task.getDescription().isEmpty()) {
            throw new DeadlineException("What is the event ABOUT?");
        } else if (curr.length == 1) {
            throw new DeadlineException("Where is the END DATE?");
        } else {
            LocalDateTime dateTime = Doge.getDateTime(curr[1].trim());
            this.task.setDescription(curr[0].trim());
            this.task.setDateTime(dateTime);
            tasks.addTask(this.task);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Stop troubling me, I've already added this task:";
    }
}
