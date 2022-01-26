package luke.commands;

import luke.data.tasks.Event;

public class EventCommand extends AddCommand {
    public static final CommandAction COMMAND_ACTION = CommandAction.EVENT;

    public EventCommand(Event event) {
        super(event);
    }
}
