package duke.command;

import duke.DukeDateTime;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.TaskList;

/**
 * A Command that adds a deadline task when executed.
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final DukeDateTime by;

    /**
     * Constructs a {@code DeadlineCommand} object.
     * @param description the description of the deadline task
     * @param by a {@code DukeDateTime} object specifying the deadline of the task
     */
    public DeadlineCommand(String description, DukeDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds a deadline task into the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) {
        Task t = new Deadline(description, by);
        tasks.add(t);
        return "Got it. I've added this task:\n  " + t
                + "\nNow you have " + tasks.size() + " task(s) in your list.";
    }

    /**
     * Indicates that the program should not be exited.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
