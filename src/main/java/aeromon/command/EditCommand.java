package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.TaskArrayList;
import aeromon.task.Task;

/**
 * EditCommand handles the edit commands which edit the tasks in the TaskArrayList.
 */
public class EditCommand extends Command {

    private final EditType editType;
    private final int taskNum;

    private static final String MARK_MESSAGE = "Naisu! You've completed: \n";
    private static final String UNMARK_MESSAGE = "OI! What happened to completing: \n";
    private static final String DELETE_MESSAGE = "Okies! I have deleted: \n";

    public enum EditType {MARK, UNMARK, DELETE}

    /**
     * Constructs the EditCommand object.
     *
     * @param editType the type of the Edit command, which is provided by the EditType enum.
     * @param taskNum  the task number to be edited.
     */
    public EditCommand(EditType editType, int taskNum) {
        this.editType = editType;
        this.taskNum = taskNum;
    }

    @Override
    public String execute(TaskArrayList taskArrayList, Storage storage) throws AeromonException {

        if (taskNum < 1 || taskNum > taskArrayList.getSize()) {
            throw new AeromonException("Nani is that task number, sir?\n");
        } else {
            int index = taskNum - 1;

            switch (editType) {
            case MARK:
                Task markTask = taskArrayList.get(index);
                markTask.markAsDone();
                storage.saveFile(taskArrayList.getTasks());
                return MARK_MESSAGE + markTask + "\n" + taskArrayList.getTasksStatus();

            case UNMARK:
                Task unmarkTask = taskArrayList.get(index);
                unmarkTask.markAsNotDone();
                storage.saveFile(taskArrayList.getTasks());
                return UNMARK_MESSAGE + unmarkTask + "\n" + taskArrayList.getTasksStatus();

            case DELETE:
                Task deleteTask = taskArrayList.delete(index);
                storage.saveFile(taskArrayList.getTasks());
                return DELETE_MESSAGE + deleteTask + "\n" + taskArrayList.getTasksStatus();

            }
            return "Ohnoz I couldn't execute the command, tHerE weRE somE ErrORss!";
        }
    }
}