package duke.command;

import duke.task.TaskList;

/**
 * Represents a command to find a given task in the task list. A
 * <code>FindCommand</code> object records the search details input
 * by the user. When executing the object, the command will return a
 * list of the matched details.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task description>";

    private final String searchInfo;

    /**
     * Creates an instance of a FindCommand object.
     *
     * @param searchInfo the details that the user is looking for
     */
    public FindCommand(String searchInfo) {
        this.searchInfo = searchInfo;
    }

    /**
     * Returns a command feedback after the execution of the
     * FindCommand which returns a new task list containing tasks with
     * matching input.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.FIND.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) {
        return new CommandFeedback(CommandType.FIND, taskList.search(searchInfo));
    }

    /**
     * Returns the command word of the FindCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
