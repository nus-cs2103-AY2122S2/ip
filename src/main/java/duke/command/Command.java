package duke.command;

import duke.DukeException;
import duke.managers.FileManager;
import duke.managers.TaskList;

public abstract class Command {
    private String userTaskString;

    public Command(String userTaskString) {
        this.userTaskString = userTaskString;
    }

    public String getUserTaskString() {
        return this.userTaskString;
    }

    /**
     * Abstract method which executes the commands' tasks
     * @param taskList
     * @param fileManager
     * @throws DukeException
     */
    public abstract void executeTask(TaskList taskList, FileManager fileManager) throws DukeException;

}
