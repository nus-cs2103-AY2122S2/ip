package duke;

/**
 * Command to unmark task
 */
public class UnmarkCommand extends Command {
    public int number;

    /**
     * Constructor for command
     * @param number index of task to be unmarked
     */
    public UnmarkCommand(int number) {
        this.number = number;
    }

    /**
     * Executes unmark command
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.unmark(number);
        return ui.showUnmark(tasks.get(number - 1));
    }

    /**
     * Boolean indicative of whether program should end
     * @return boolean indicative of whether program should end
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
