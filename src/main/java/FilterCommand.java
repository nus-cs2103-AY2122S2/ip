package tesseract.command;

import tesseract.command.Command;

import tesseract.main.Date;
import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;

/**
 * Represent a command to filter tasks according to a condition.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class FilterCommand extends Command {
    protected static final int KEYWORD = 2;
    protected static final int CONDITION = 3;

    /** The type of condition for filtering */
    protected String keyword; // filter by this condition, e.g. "filter /by date YYYY-MM-DDDD"
    /** The specific condition for filtering tasks */
    protected String condition; // description of the condition, e.g. date

    FilterCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.keyword = cmdArr[KEYWORD];
        this.condition = cmdArr[CONDITION];
    }

    /**
     * Execute the command on the system to filter tasks according to condition.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
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
