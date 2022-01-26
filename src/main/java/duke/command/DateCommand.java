package duke.command;

import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStoring;
import duke.util.Ui;

/**
 * Extracts events on a specific date command
 */
public class DateCommand extends duke.command.Command {
    private DateTable dateTable;
    private String description;

    public DateCommand(DateTable dateTable, String description) {
        this.dateTable = dateTable;
        this.description = description;
    }

    /**
     * Extract the events from the <code>DateTime</code> for a specific date
     * using command description
     *
     * @param taskList Reference of the <code>TaskList</code> object
     * @param ui Reference of the <code>Ui</code> object
     * @param botStorage Reference of the <code>BotStorage</code> object
     * @param dateTable Reference of the <code>DateTable</code> object
     */
    @Override
    public void execute(TaskList taskList, Ui ui, BotStoring botStorage, DateTable dateTable) {
        dateTable.getEventOnDate(description);
    }

    /**
     * Checks if the command is the exit command
     *
     * @return False as this is not an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
