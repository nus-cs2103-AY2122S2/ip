package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class DeleteCommand extends Command {
    private int taskIdx;

    /**
     * Constructor for DeleteCommand
     *
     * @param userTaskString
     * @param taskIdx
     */
    public DeleteCommand(String userTaskString, int taskIdx) {
        super(userTaskString);
        this.taskIdx = taskIdx;
    }

    /**
     * Executes deletion of task per taskIdx
     *
     * @param taskList
     * @param fileManager
     * @throws DukeException if task idx is not valid
     */
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.deleteTask(this.taskIdx);
    }

}


