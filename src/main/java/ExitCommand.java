package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.main.TesseractException;

public class ExitCommand extends Command{
    protected String cmdWord;

    ExitCommand(String[] cmdArr) {
        super(cmdArr[0]);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        if (storage.isUpdated()) {
            try {
                storage.updateStorage(taskList, taskList.size());
            } catch (TesseractException e) {
                ui.showError(e.getErrMsg());
                ui.admitBug();
            }
        }
        ui.sayBye();
    };
}
