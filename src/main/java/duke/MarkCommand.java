package duke;

/**
 * Command to mark task
 */
public class MarkCommand extends Command {
    public int number;

    /**
     * Constructor for command
     * @param number index of task to be marked
     */
    public MarkCommand(int number) {
        this.number = number;
    }

    /**
     * Executes mark command
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        tasks.mark(number);
        return ui.showMark(tasks.get(number));
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
