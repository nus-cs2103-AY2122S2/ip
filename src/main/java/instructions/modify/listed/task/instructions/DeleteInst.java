package instructions.modify.listed.task.instructions;

/**
 * This class represents a Delete Task Instruction.
 * Format: "Delete x", where x is an integer.
 *
 * @author Ong Han Yang
 */
public class DeleteInst extends ModifyListedTaskInst {
    /**
     * Private constructor for a Delete Task Instruction.
     *
     * @param taskNum the task number to delete.
     */
    private DeleteInst(int taskNum) {
        super(taskNum);
    }

    /**
     * Factory method to produce a Delete Task Instruction.
     *
     * @param taskNum the task number to delete.
     * @return the Delete Task Instruction.
     */
    protected static DeleteInst of(int taskNum) {
        return new DeleteInst(taskNum);
    }
}
