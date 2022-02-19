package duke;

import java.time.LocalDate;

/**
 * Command to add deadline
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructor for deadline command
     * @param description deadline description
     * @param by deadline date
     * @param time deadline time
     */
    public DeadlineCommand(String description, LocalDate by, String time) {
        this.deadline = new Deadline(description, by, time);
    }

    /**
     * Executes command to add deadline
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.add(deadline);
        return ui.showDeadline(this.deadline, tasks);
    }

    /**
     * Boolean indicative of whether program should end
     * @return boolean indicative of whether program should end
     */
    public boolean isExit() {
        return false;
    }
}
