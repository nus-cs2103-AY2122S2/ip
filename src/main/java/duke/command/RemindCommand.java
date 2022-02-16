package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class RemindCommand extends Command {

    /** The default number of days the reminder should cover. **/
    public static final int DEFAULT_NUM_DAYS = 7;

    private int daysFromToday;

    public RemindCommand(int daysFromToday) {
        this.daysFromToday = daysFromToday;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList upcomingTasks = taskList.getUpcomingTasks(daysFromToday);
        return ui.showRemindResult(upcomingTasks, daysFromToday);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
