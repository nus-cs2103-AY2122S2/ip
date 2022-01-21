package commands;

import task.ListTask;
import java.time.LocalDateTime;

public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final boolean isDateGiven;
    private final LocalDateTime date;

    public ListCommand() {
        this.isDateGiven = false;
        this.date = null;
    }

    public ListCommand(boolean isDateGiven, LocalDateTime date) {
        this.isDateGiven = isDateGiven;
        this.date = date;
    }

    @Override
    public String execute(ListTask tasks) {
        if (isDateGiven) {
            return tasks.generateTaskList(date);
        } else {
            return tasks.generateTaskList();
        }
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
