package van;

/**
 * Abstracts commands that change the completion status of tasks
 */
public class MarkCommand implements Command {
    private boolean isDone;
    private int index;

    /**
     * Constructs a MarkCommand object to change completion status of task
     *
     * @param isDone status to change completion status of task to
     * @param index  index number of task status to be changed
     */
    public MarkCommand(boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Calls the methods needed in order to change completion status of task
     *
     * @param ui       Ui object to handle print tasks
     * @param taskList TaskList object that handles managing of the list of tasks
     * @param storage  Storage object that handles loading and saving of list
     * @return returns true only when command executed is an ExitCommand
     */
    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        try {
            if (index > taskList.getTaskCount()) {
                throw new VanException("Task number out of range");
            }
            if (isDone) {
                taskList.markDone(index);
            } else {
                taskList.markUndone(index);
            }
        } catch (VanException ex) {
            System.out.println(ex);
        }
        return false;
    }
}
