package duke;

public class ExitCommand extends Command {

    /**
     * Executes command to exit program
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        return ui.showExit(storage, tasks);
    }

    /**
     * Boolean indicative of whether program should end
     * @return boolean indicative of whether program should end
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
