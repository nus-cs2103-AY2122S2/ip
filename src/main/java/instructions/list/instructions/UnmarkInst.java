package instructions.modify.listed.task.instructions;

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
}
