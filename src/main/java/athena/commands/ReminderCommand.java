package athena.commands;

import java.time.LocalDate;
import java.util.List;

import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a reminder command given to Athena by the user. When executed, lists
 * all tasks with start dates falling within the given range.
 */
public class ReminderCommand extends Command {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a new ReminderCommand instance with the given startDate and endDate.
     *
     * @param startDate The start of the time period to look for tasks in.
     * @param endDate The end of the time period to look for tasks in.
     */
    public ReminderCommand(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Looks for tasks falling within the time period given and returns the results.
     *
     * @param taskList The taskList instance to look for tasks in.
     * @return Command output.
     */
    @Override
    public String execute(TaskList taskList) {
        List<Integer> taskNumbers = taskList.getTaskNumbersWithinTimeWindow(startDate, endDate);
        if (taskNumbers.size() == 0) {
            return Messages.NO_REMINDERS_MESSAGE;
        }
        return Messages.getRemindersFoundInRangeDialog(taskList, taskNumbers, startDate, endDate);
    }
}
