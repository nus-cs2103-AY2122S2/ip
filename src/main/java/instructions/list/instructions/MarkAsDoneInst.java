package instructions.modify.listed.task.instructions;

/**
 * This class represents a Mark Task as Done Instruction.
 * Format: "mark x", where x is the task number to be marked done.
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
    protected static MarkAsDoneInst of(int taskNum) {
        return new MarkAsDoneInst(taskNum);
    }
}
