package duke;

public class ListCommand extends Command {

    /**
     * Executes command to show list of tasks
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return ui.showTasks(tasks);
    }

    /**
     * Boolean indicative of whether program should end
     * @return boolean indicative of whether program should end
     */
    public boolean isExit() {
        return false;
    }
}
