package instructions.taskinstructions.listinstructions;

import exceptions.InvalidActionException;
import exceptions.NoSuchTaskException;
import tasks.TaskList;

/**
 * This class represents a Mark Task as Done Instruction.
 * <br>
 * Format: {@code mark < task number >}
 *
 * @author Ong Han Yang
 */
public class MarkAsDoneInst extends ModifyListedTaskInst {
    /**
     * Constructs a Mark Task as Done Instruction.
     *
     * @param taskNum the task number to mark as done.
     */
    private MarkAsDoneInst(int taskNum) {
        super(taskNum);
    }

    /**
     * Produces a Mark Task as Done Instruction.
     *
     * @param taskNum the task number to mark as done.
     * @return the Mark Task as Done Instruction.
     */
    public static MarkAsDoneInst of(int taskNum) {
        return new MarkAsDoneInst(taskNum);
    }

    /**
     * Marks a task from the taskList as done.
     *
     * @param taskList the taskList to modify.
     * @return the feedback message after performing this instruction.
     * @throws NoSuchTaskException when there does not exist a task with the given index.
     * @throws InvalidActionException when the user tries to mark an already marked instruction.
     */
    @Override
    public String doInst(TaskList taskList) throws NoSuchTaskException, InvalidActionException {
        taskList.markTask(super.getTaskNum() - 1, true, true);
        return String.format("Okay, this task is done:\n%s",
                taskList.displayTask(super.getTaskNum() - 1));
    }
}
