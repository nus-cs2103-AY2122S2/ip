package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.Task;
import aeromon.TaskArrayList;

/**
 * EditCommand handles the commands which edit the tasks in the TaskArrayList.
 */
public class EditCommand extends Command {

    private EditType editType;
    private int taskNum;

    private static final String MARK_MESSAGE = "Naisu! You've completed: \n";
    private static final String UNMARK_MESSAGE = "OI! What happened to completing: \n";
    private static final String DELETE_MESSAGE = "check your delete message \n";

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
    public String execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {

        if (taskNum < 1 || taskNum > taskArrayList.getSize()) {
            throw new AeromonException("Nani is that task number, sir?\n");
        } else {
            int index = taskNum - 1;

            switch (editType) {
            case MARK:
                Task markTask = taskArrayList.get(index);
                markTask.markAsDone();
                storage.saveFile(taskArrayList.getTasks());
                return MARK_MESSAGE + markTask + "\n";

            case UNMARK:
                Task unmarkTask = taskArrayList.get(index);
                unmarkTask.markAsNotDone();
                storage.saveFile(taskArrayList.getTasks());
                return UNMARK_MESSAGE + unmarkTask + "\n";

            case DELETE:
                Task deleteTask = taskArrayList.delete(index);
                storage.saveFile(taskArrayList.getTasks());
                return DELETE_MESSAGE + deleteTask + "\n";

            }
            return "Ohnoz I couldn't execute the command, tHerE weRE somE ErrORss!";
        }
    }
}