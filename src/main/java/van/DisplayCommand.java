package van;

/**
 * DisplayCommand abstracts the displaying of all tasks currently in the listw
 */
public class DisplayCommand implements Command {

    /**
     * Calls the methods needed in order to print all tasks in the list
     *
     * @param ui       Ui object to handle print tasks
     * @param taskList TaskList object that handles managing of the list of tasks
     * @param storage  Storage object that handles loading and saving of list
     * @return returns true only when command executed is an ExitCommand
     */
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        ui.printList(taskList.getList());
        return false;
    }
}
