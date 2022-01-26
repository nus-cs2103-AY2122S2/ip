package aeromon.command;

import aeromon.AeromonException;
import aeromon.Storage;
import aeromon.Ui;
import aeromon.task.Task;
import aeromon.task.TaskArrayList;

public class EditCommand extends Command {

    private EditType editType;
    private int taskNum;

    public enum EditType { MARK, UNMARK, DELETE}

    public EditCommand(EditType editType, int taskNum) {
        this.editType = editType;
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskArrayList taskArrayList, Ui ui, Storage storage) throws AeromonException {
        if (taskNum < 1 || taskNum > taskArrayList.getSize()) {
            throw new AeromonException("Nani is that task number, sir?\n");
        } else {
            switch (editType) {
                case MARK: {
                    int index = taskNum - 1;
                    Task temp = taskArrayList.get(index);
                    temp.markAsDone();
                    storage.saveFile(taskArrayList.getTasks());
                    break;
                }
                case UNMARK: {
                    int index = taskNum - 1;
                    Task temp = taskArrayList.get(index);
                    temp.markAsNotDone();
                    storage.saveFile(taskArrayList.getTasks());
                    break;
                }
                case DELETE: {
                    int index = taskNum - 1;
                    taskArrayList.remove(index);
                    storage.saveFile(taskArrayList.getTasks());
                    break;
                }
            }
        }
    }


}