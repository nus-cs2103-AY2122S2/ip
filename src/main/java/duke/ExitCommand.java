package duke;

public class ExitCommand extends Command {

    /**
     * Executes command to exit program
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        ui.showExit(storage, tasks);
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
