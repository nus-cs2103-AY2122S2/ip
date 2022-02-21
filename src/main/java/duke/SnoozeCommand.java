package duke;


/**
 * Command to delete task
 */
public class SnoozeCommand extends Command {
    public int number;

    /**
     * Constructor for command
     * @param number index of task to be deleted
     */
    public SnoozeCommand(int number) {
        this.number = number;
    }

    /**
     * Executes command
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.snooze(number);
        return ui.showSnooze(tasks.get(number - 1));
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
