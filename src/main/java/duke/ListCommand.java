package duke;

/**
 * class to list commands
 */
public class ListCommand extends Command {
    /**
     * Execute ListCommand
     * @param taskList list to store all tasks
     * @param ui display output
     * @param storage store tasks
     * @throws ExceptionHandler
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ExceptionHandler {
        if (taskList.getSize() <= 0) {
            throw new ExceptionHandler("No tasks in the list");
        }
        int count = 1;
        for (Task t : taskList.getListOfTasks()) {
            ui.println(count + ". " + t);
            count++;
        }
    }

    /**
     * Method to check for exit command
     * @return A boolean to check if an exit command is entered
     */
    public static boolean isExit() {
        return false;
    }
}
