package instructions.list.instructions;

import Exceptions.NoSuchTaskException;
import tasks.TaskList;

/**
 * This class represents a Mark Task as Undone Instruction.
 * Format: "unmark x", where x is the task number to be marked undone.
 *
 * @author Ong Han Yang
 */
public class UnmarkInst extends ModifyListedTaskInst {
    /**
     * Constructs a Mark Task as Undone Instruction.
     *
     * @param taskNum the task number to mark as undone.
     */
    private UnmarkInst(int taskNum) {
        super(taskNum);
    }

    /**
     * Produces a Mark Task as Undone Instruction.
     *
     * @param taskNum the task number to mark as undone.
     * @return the Mark Task as Undone Instruction.
     */
    protected static UnmarkInst of(int taskNum) {
        return new UnmarkInst(taskNum);
    }

    /**
     * Marks a task from the taskList as undone.
     *
     * @param taskList the taskList to modify.
     * @return the feedback message after performing this instruction.
     * @throws NoSuchTaskException when there does not exist a task with the
     *                             given index.
     */
    @Override
    public String doInst(TaskList taskList) throws NoSuchTaskException {
        taskList.markTask(super.getTaskNum() - 1, false);
        return String.format("Okay, this task needs to be done:\n%s"
                , taskList.displayTask(super.getTaskNum() - 1));
    }
}
