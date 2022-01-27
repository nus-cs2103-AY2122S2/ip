package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Date;
import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;

public class FilterCommand extends Command {
    protected static final int KEYWORD = 2;
    protected static final int CONDITION = 3;

    protected String keyword; // filter by this condition, e.g. "filter /by date YYYY-MM-DDDD"
    protected String condition; // description of the condition, e.g. date

    FilterCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.keyword = cmdArr[KEYWORD];
        this.condition = cmdArr[CONDITION];
    }

    @Override
    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        if (this.keyword.equals("date")) {
            Date date = new Date(this.condition);
            ui.filterRes(this.condition, taskList.filterByDate(date).toString());
        } else {
            ui.admitBug();
        }
    };
}
