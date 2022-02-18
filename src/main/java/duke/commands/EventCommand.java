package duke.commands;

import duke.info.task.Event;

public class EventCommand extends AddCommand {

    /**
     * Constructs an EventCommand with the specified event and date of the event.
     * @param event - the event to be added
     * @param date - the date of the event
     */
    public EventCommand(String event, String date) {
        super(new Event(event, date, false));
    }

}
