package duke.command.add;

import duke.task.Deadline;

/**
 * Represents a command to add a deadline task into the task list. A
 * <code>DeadlineCommand</code> object records the deadline task input
 * by the user. When executing the object, the deadline task being
 * stored will be added into the task list.
 */
public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task name> /by dd-MM-yyyy HHmm";

    /**
     * Creates an instance of a DeadlineCommand object.
     *
     * @param newDeadline the new deadline task being added to the task list.
     */
    public DeadlineCommand(Deadline newDeadline) {
        super(newDeadline);
    }

    /**
     * Returns the command word of the DeadlineCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
