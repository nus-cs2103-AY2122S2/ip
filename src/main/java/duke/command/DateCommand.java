package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class DateCommand extends duke.command.Command {
    private DateTable dateTable;
    private String description;

    public DateCommand(DateTable dateTable, String description) {
        this.dateTable = dateTable;
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage, DateTable dateTable) {
        dateTable.getEventOnDate(description);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
