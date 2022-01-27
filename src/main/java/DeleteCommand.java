package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;

import tesseract.task.Task;

public class DeleteCommand extends Command {
    protected static final int KEYWORD = 1;
    protected int index;

    DeleteCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        Task task = taskList.deleteTask(this.index);
        storage.needUpdate();
        ui.deleteTaskRes(task.toString(), taskList.size());
    };
}
