/**
 * HelpCommand class is a Command that contains instructions
 * to run when user inputs an invalid command.
 */
public class HelpCommand extends Command {

    /**
     * Helps the user find available commands.
     *
     * @param tasks TaskList containing all the tasks.
     * @param ui Ui class for invoking user feedback.
     * @param storage Storage class used to save files.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }

    /**
     * Used to exit the Jeff program.
     *
     * @return false to keep running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
