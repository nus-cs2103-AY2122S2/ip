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
    private int taskIndex;

    /**
     * Constructor for ModifyCommand
     *
     * @param inputIndex 0-based index of Task object to be referenced in Storage
     * @param inputAction Action to be applied to Tast at inputIndex in Storage
     */
    public ModifyCommand(int inputIndex, ModifyType inputAction) {
        taskIndex = inputIndex;
        modifyAction = inputAction;
    }

    /**
     * Applies action to Task in Storage at indicated taskIndex and modifyAction.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return Output String to indicate success and task modified
     * @throws KarenException if Task is not within the index of the Storage
     */
    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        try {
            Task getTask = storage.getTask(taskIndex);
            String outputResult = null;

            switch (modifyAction) {
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
            default:
                outputResult = null;
            }

            // outputResult should not plausibly be null. Constructing ModifyCommand objects should
            // only be done based on enums available in modifyAction.
            assert outputResult != null;

            return outputResult;
        } catch (IndexOutOfBoundsException err) {
            throw new KarenException(
                    String.format(InvalidMessage.INVALID_INDEX.toString(), taskIndex + 1));
        }
    }
}
