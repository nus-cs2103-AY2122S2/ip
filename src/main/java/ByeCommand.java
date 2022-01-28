public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    /**
     * Executes command by printing exit message.
     *  @param taskList List of tasks.
     * @param ui       Ui provided.
     * @param storage  Saved history.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
    }
}
