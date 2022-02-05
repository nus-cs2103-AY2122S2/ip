package duke;

/**
 * class for exiting
 */
public class ExitCommand extends Command {
    /**
     * Execute exit command
     * @param taskList list to store all tasks
     * @param ui display output
     * @param storage store tasks
     * @throws ExceptionHandler
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ExceptionHandler {
        ui.println("Bye. Hope to see you again soon!");
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public static boolean isExit() {
        return true;
    }
}
