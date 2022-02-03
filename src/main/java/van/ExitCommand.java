package van;

/**
 * ExitCommand abstracts commands to save and close the chat bot
 */
public class ExitCommand implements Command {

    /**
     * Calls the methods needed in order to save existing tasks and
     * close the chat bot
     *
     * @param ui       Ui object to handle print tasks
     * @param taskList TaskList object that handles managing of the list of tasks
     * @param storage  Storage object that handles loading and saving of list
     * @return returns true only when command executed is an ExitCommand
     */
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        storage.saveTasks(taskList.getList());
        ui.exitMessage();
        System.exit(0);
        return true;
    }
}
