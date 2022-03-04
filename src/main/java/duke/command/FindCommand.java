package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;

public class FindCommand extends Command {
    private String userFindTask;

    /**
     * Constructor for FindCommand
     *
     * @param userTaskString
     * @param userFindTask
     */
    public FindCommand(String userTaskString, String userFindTask) {
        super(userTaskString);
        this.userFindTask = userFindTask;
    }

    @Override
    public void executeTask(TaskList taskList, FileManager fileManager) throws DukeException {
        taskList.printUserTasks(this.userFindTask);
    }


}
