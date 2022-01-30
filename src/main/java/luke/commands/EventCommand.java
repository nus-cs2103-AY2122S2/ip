package luke.commands;

import luke.data.tasks.Event;

/**
 * Implements the event command.
 */
public class EventCommand extends AddCommand {

    /**
     * Constructs the event command with the specified event task.
     *
     * @param event The specified event task to be added.
     */
    public EventCommand(Event event) {
        super(event);
    }
}
