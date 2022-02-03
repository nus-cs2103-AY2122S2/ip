package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;
import karen.task.Task;

/**
 * To modify Task objects in Storage.
 */
public class ModifyCommand extends Command {
    public static final String DONE_FORMAT = "This task is finally done:\n  %s";
    public static final String UNDONE_FORMAT = "This task is now incomplete - unacceptable:\n  %s";

    protected ModifyType modifyAction;
    private final int taskIndex;

    public ModifyCommand (int taskIndex, ModifyType modifyAction) {
        this.taskIndex = taskIndex;
        this.modifyAction = modifyAction;
    }

    /**
     * Applies action to Task in Storage at indicated taskIndex and modifyAction
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return Output String to indicate success and task modified
     * @throws KarenException
     */
    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        try {
            Task getTask = storage.getTask(this.taskIndex);
            String outputResult = null;

            switch (this.modifyAction) {
            case MARK:
                getTask.markDone();
                storage.saveTasks();
                outputResult = ui.displayUserInput(String.format(DONE_FORMAT, getTask));
                break;
            case UNMARK:
                getTask.markUndone();
                storage.saveTasks();
                outputResult = ui.displayUserInput(String.format(UNDONE_FORMAT, getTask));
                break;
            }
            return outputResult;
        } catch (IndexOutOfBoundsException err) {
            throw new KarenException(
                    String.format("Are you sure that [%d] is even in the 'list' command?", this.taskIndex + 1));
        }
    }
}
