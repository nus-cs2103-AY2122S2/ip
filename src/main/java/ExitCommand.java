/**
 * Responsible for the functionality needed when exiting the chatbot.
 */
public class ExitCommand extends Command{

    /**
     * Constructor to create Exit Command.
     */
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    /**
     * Outputs the exit message via the user interface.
     *
     * @param taskList list of tasks.
     * @param ui user interface of the chatbot.
     * @param storage storage used by chatbot.
     */
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
            ui.showExit();
    }
}
