package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;

public class ListCommand extends Command {

    ListCommand(String[] cmdArr) {
        super(cmdArr[0]);
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        ui.listTasks(taskList.toString());
    };
}
