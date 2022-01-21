package duke.commands;

import duke.info.task.Event;

public class EventCommand extends AddCommand {

    public EventCommand(String event, String date) {
        super(new Event(event, date, false));
    }

}
