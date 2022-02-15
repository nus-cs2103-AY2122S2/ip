package duke.command.add;

import duke.task.Event;

/**
 * Represents a command to add an event task into the task list. An
 * <code>EventCommand</code> object records the event task input
 * by the user. When executing the object, the event task being
 * stored will be added into the task list.
 */
public class EventCommand extends AddCommand {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task name> /at dd-MM-yyyy HHmm-HHmm";

    /**
     * Creates an instance of an EventCommand object.
     *
     * @param newEvent the new event task being added to the task list.
     */
    public EventCommand(Event newEvent) {
        super(newEvent);
    }

    /**
     * Returns the command word of the EventCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
