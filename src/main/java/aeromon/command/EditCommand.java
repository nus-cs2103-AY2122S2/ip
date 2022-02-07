package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.task.Task;
import aeromon.task.TaskArrayList;
import aeromon.Ui;

/**
 * EditCommand handles the commands which edit the tasks in the TaskArrayList.
 */
public class EditCommand extends Command {

    private EditType editType;
    private int taskNum;

    public enum EditType { MARK, UNMARK, DELETE}

    /**
     * Constructs the EditCommand object.
     * @param editType the type of the Edit command, which is provided by the EditType enum.
     * @param taskNum the task number to be edited.
     */
    public EditCommand(EditType editType, int taskNum) {
        this.editType = editType;
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {

        if (taskNum < 1 || taskNum > taskArrayList.getSize()) {
            throw new AeromonException("Nani is that task number, sir?\n");
        } else {
            int index = taskNum - 1;

            switch (editType) {
            case MARK:
                Task markTask = taskArrayList.get(index);
                markTask.markAsDone();
                storage.saveFile(taskArrayList.getTasks());
                break;

            case UNMARK:
                Task unmarkTask = taskArrayList.get(index);
                unmarkTask.markAsNotDone();
                storage.saveFile(taskArrayList.getTasks());
                break;

            case DELETE:
                taskArrayList.delete(index);
                storage.saveFile(taskArrayList.getTasks());
                break;

            }
        }
    }
}