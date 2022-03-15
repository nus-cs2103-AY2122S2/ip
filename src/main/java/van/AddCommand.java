package van;

/**
 * AddCommand abstracts the adding of the different types of tasks.
 */
public class AddCommand implements Command {
    private Task newTask;

    /**
     * Constructs a AddCommand object to encapsulate the task to be added
     *
     * @param newTask new task to be added to list
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Calls the methods needed in order to add a new task to the list
     *
     * @param ui       Ui object to handle print tasks
     * @param taskList TaskList object that handles managing of the list of tasks
     * @param storage  Storage object that handles loading and saving of list
     * @return returns true only when command executed is an ExitCommand
     */
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        taskList.addTask(newTask);
        ui.taskMessage(newTask, taskList.getTaskCount());
        return false;
    }

    /**
     * Gets the string representation of the task details
     *
     * @return String containing the details about the new task
     */
    @Override
    public String toString() {
        return newTask.getStatus();
    }

}
