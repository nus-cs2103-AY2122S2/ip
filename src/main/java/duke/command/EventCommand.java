package duke.command;

import duke.task.Event;

public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task name> /at dd-MM-yyyy HHmm-HHmm";

    public EventCommand(Event newEvent) {
        super(newEvent);
    }
}
