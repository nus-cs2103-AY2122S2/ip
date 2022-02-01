package doge.command;

import doge.DateTime;
import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;
import doge.exception.EventException;
import doge.task.Task;

import java.time.LocalDateTime;

/**
 * Represents the "event" command. Doge bot will add a task of "event" type into the TaskList.
 */
public class EventCommand extends Command {

    /**
     * Constructor for class EventCommand.
     *
     * @param task the event task that is associated with this command
     */
    public EventCommand(Task task) {
        super(task);
    }

    /**
     * Executes the "event" command. It adds a task of "event" type into the TaskList.
     *
     * @param tasks {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @throws DogeException if it fails to add the "event" task into the TaskList or user failed to provide
     * necessary details to initialise an Event task
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        String[] curr = this.task.getDescription().split("/");

        if (this.task.getDescription().isEmpty()) {
            throw new EventException("What is the event ABOUT?");
        } else if (curr.length == 1) {
            throw new EventException("Where is the END DATE?");
        } else {
            LocalDateTime dateTime = DateTime.getDateTime(curr[1].trim());
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
