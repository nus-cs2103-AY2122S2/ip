package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;

/**
 * Command to find a task by searching for a keyword.
 */
public class FindCommand extends Command {
    public static final String FIND_COMMAND = "find";

    private static final String NO_INPUT =
            "I can't find the tasks you want if YOU DON'T TELL ME WHAT TO FIND. GIVE ME KEYWORDS!!";

    /**
     * Constructor for find command to init values.
     *
     * <p>Calls superclass Command constructor.</p>
     */
    public FindCommand() {
        super(FIND_COMMAND);
    }

    /**
     * Execution behavior of the find command.
     *
     * <p> Finds a task in the tasklist by searching for a keyword.</p>
     *
     * @param input User input
     * @param taskList User tasklist.
     * @param storage Storage to store the updated tasklist.
     * @return Tasks with the keyword.
     * @throws DukeException If no task description.
     */
    @Override
    public String execute(String input, TaskList taskList, Storage storage) throws DukeException {
        String taskDescription = getTaskDescription(input, NO_INPUT);

        TaskList filteredTasks = taskList.getTaskListWithKeyword(taskDescription);
        if (filteredTasks.getTaskListSize() == 0) {
            return "No tasks with the keyword";
        }

        return "Here are the matching tasks in your list base on the keyword "
                + taskDescription + ": \n" + filteredTasks.getTaskListStr();
    }
}
