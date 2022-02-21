package duke;

/**
 * Command to delete task
 */
public class DeleteCommand extends Command {
    public int number;

    /**
     * Constructor for command
     * @param number index of task to be deleted
     */
    public DeleteCommand(int number) {
        this.number = number;
    }

    /**
     * Executes command
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        Task temp = tasks.get(number - 1);
        tasks.delete(number);
        return ui.showDelete(temp, tasks);
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
