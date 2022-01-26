package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class UnmarkCommand extends Command{
    private int taskIdx;

    public UnmarkCommand(String userTaskString, int taskIdx) {
        super(userTaskString);
        this.taskIdx = taskIdx;
    }

    /**
     * Unmarks task as per taskIdx
     *
     * @param taskList
     * @param fileManager
     * @throws DukeException
     */
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.markTaskNotDone(taskIdx, true);
    }
}

