public class ExitCommand extends Command {
    /**
     *
     * @param taskList
     * @param ui
     * @param storage
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        super.toggleExit();
    }
}