package commands;

import task.ListTask;

import java.time.LocalDateTime;

/**
 * Lists tasks in task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final boolean isDateGiven;
    private final LocalDateTime date;


    /**
     * Constructor for class ListCommand.
     */
    public ListCommand() {
        this.isDateGiven = false;
        this.date = null;
    }

    /**
     * Constructor for class DeleteCommand.
     *
     * @param isDateGiven list the tasks with date or not.
     * @param date        list all tasks before this date.
     */
    public ListCommand(boolean isDateGiven, LocalDateTime date) {
        this.isDateGiven = isDateGiven;
        this.date = date;
    }

    /**
     * Returns list of tasks message.
     *
     * @param tasks task list.
     * @return list of tasks message.
     */

    @Override
    public String execute(ListTask tasks) {
        if (isDateGiven) {
            return tasks.generateTaskList(date);
        } else {
            return tasks.generateTaskList();
        }
    }

    /**
     * Returns command word.
     *
     * @return command word.
     */
    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
