public class ExitCommand extends Command {
    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        super.toggleExit();
        return "Exiting.";

    }
}