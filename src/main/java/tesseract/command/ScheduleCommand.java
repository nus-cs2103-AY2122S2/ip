package tesseract.command;

import tesseract.main.Date;
import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;

/**
 * Represent a command to display the schedule on a given date.
 * @author Fan Jue
 * @version 0.2.0
 * @since 0.1.0
 */
public class ScheduleCommand extends Command {
    protected static final int DATE = 1;
    private final String dateString;
    private final Date date;

    ScheduleCommand(String[] cmdArr) {
        super(cmdArr[0]);
        this.dateString = cmdArr[DATE];
        this.date = new Date(dateString);
    }

    /**
     * Execute the command on the system to display schedule on a given date.
     *
     * @param taskList The list of all current tasks.
     * @param ui The user interface.
     * @param storage The memory storage.
     */
    @Override
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        return ui.scheduleRes(dateString, taskList.filterByDate(date).toString());
    }
}
