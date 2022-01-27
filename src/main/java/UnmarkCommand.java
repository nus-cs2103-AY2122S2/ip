package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;

public class UnmarkCommand extends Command {
    protected static final int KEYWORD = 1;
    protected int index;

    UnmarkCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.index = Integer.parseInt(cmdArr[KEYWORD]);
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        taskList.markAsUndone(this.index);
        ui.markAsUndoneRes(taskList.get(index).toString());
    };
}
