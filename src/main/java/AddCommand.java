import java.io.IOException;

/**
 * Extends Command class
 * When executed, calls Ui method to print output
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor
     * Initialise task attribute to the input task to be added
     * @param task Task to be added
     */
    public AddCommand (Task task) {
        this.task = task;
    }

    /**
     * Handles adding of task to tasklist, calling of ui method to print outpyt and ypdating storage
     * @param taskList TaskList has all current tasks
     * @param ui Ui handles printing to output
     * @param storage Storage saves tasklist
     * @return String output from ui
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.addTask(this.task);
        storage.writeToFile(taskList);
        return ui.printTaskIsAdded(this.task);
    }
}
