package duke.command;

import duke.managers.FileManager;
import duke.managers.TaskList;

public class ListCommand extends Command {
    private int taskIdx;

    /**
     * Consturctor for ListCommand
     *
     * @param userTaskString
     */
    public ListCommand(String userTaskString) {
        super(userTaskString);
    }

    /**
     * List current remaining user tasks.
     *
     * @param taskList
     * @param fileManager
     */
    public void executeTask(TaskList taskList, FileManager fileManager) {
        taskList.printUserTasks();
    }

}


