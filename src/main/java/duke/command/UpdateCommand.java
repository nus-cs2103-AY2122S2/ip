package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.tasklist.TaskList;

/**
 * Represents a command to mark task as done.
 */
public class UpdateCommand extends Command {
    private int taskIndex;
    private String newTaskDescription;
    /**
     * Creates a MarkCommand.
     *
     * @param updateInfo Position of task in the list of task.
     * @throws DukeException If taskPosition is empty or of invalid format.
     */
    public UpdateCommand(String updateInfo) throws DukeException {
        super(updateInfo);
        String[] updateInfoStrings = updateInfo.trim().split(" ", 2);
        String taskPosition = updateInfoStrings[0];
        String taskDescription = updateInfoStrings.length == 2 ? updateInfoStrings[1] : "";
        if (taskPosition == "" || taskDescription == "") {
            throw new EmptyDescriptionException("OOPS!!! The update description must include"
                    + "{task number} and {new description}.");
        }
        try {
            this.newTaskDescription = taskDescription;
            int taskNumber = Integer.valueOf(taskPosition);
            this.taskIndex = taskNumber - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input number after update");
        }
    }

    /**
     * Execute MarkCommand to mark task as done in a TaskList.
     *
     * @param taskList The TaskList for command act on.
     * @throws DukeException If taskList does not contain the task.
     * @return a String when command is called.
     */
    @Override
    public String callCommand(TaskList taskList) throws DukeException {
        return taskList.updateTask(this.taskIndex, this.newTaskDescription);
    }
}
