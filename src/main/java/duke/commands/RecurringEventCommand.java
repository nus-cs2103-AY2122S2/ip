package duke.commands;

import duke.info.task.Event;

public class RecurringEventCommand extends RecurringAddCommand {
    /**
     * Constructs a RecurringEventCommand with the given parameters
     * @param event - title of the recurring event
     * @param date - date of the first event
     * @param interval - interval between recurrences
     * @param endDate - date that the recurrences end
     */
    public RecurringEventCommand(String event, String date, int interval, String endDate) {
        super(new Event(event, date, false).getRecurrences(interval, endDate));
    }
}
