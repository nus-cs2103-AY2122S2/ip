/**
 * Represents a list command.
 *
 * @author snoidetx
 * @version 0.0.0
 */
public class ListCommand implements Command {
    private static final String COMMAND_LIST = "list";

    /**
     * Creates a list command.
     *
     * @param input User input.
     * @throws UltoiException If there are extra characters in user input.
     */
    public ListCommand(String input) throws UltoiException {
        if (! input.equals(COMMAND_LIST)) {
            throw UltoiException.commandMismatchException();
        }
    }

    /**
     * Show the list of tasks.
     *
     * @param ui UltoiUi used.
     * @param tasks List of tasks.
     * @param storage Storage used to access memory.
     */
    @Override
    public void execute(UltoiUi ui, TaskList tasks, Storage storage) {
        ui.showMsg(tasks.toString());
        return;
    }
}