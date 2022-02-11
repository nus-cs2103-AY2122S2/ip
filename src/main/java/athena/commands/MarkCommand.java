package athena.commands;

import java.util.ArrayList;

import athena.exceptions.InputErrorCode;
import athena.exceptions.InputException;
import athena.tasks.TaskList;
import athena.ui.Messages;

/**
 * Represents a mark command given to Athena by the user. When executed, marks
 * the task corresponding to the given task number as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a new MarkCommand instance with the given task number.
     *
     * @param taskNumber The number of the task to be marked as complete.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task corresponding to the given task number as done.
     *
     * @param taskList The taskList instance to mark the task as complete in.
     * @return Command output.
     * @throws InputException If the given task number is invalid/out of range.
     */
    @Override
    public String execute(TaskList taskList) throws InputException {
        if (taskList.isValidTaskNumber(taskNumber)) {
            taskList.setTaskAsDone(taskNumber);
            ArrayList<String> outputs = new ArrayList<>();
            outputs.add("Alright, I've marked the following task as done:");
            outputs.add(taskList.getTaskString(taskNumber));
            return Messages.getMultiLineString(outputs);
        } else {
            throw new InputException(InputErrorCode.INVALID_TASK_NUMBER);
        }
    }
}
