package instructions;

import exceptions.NoSuchTaskException;
import tasks.TaskList;

/**
 * This class represents an exit instruction.
 * <br>
 * Format: {@code bye}
 *
 * @author Ong Han Yang
 */
public class ExitInst extends Instruction {
    /**
     * Use a fixed/static instruction as there are no differences between multiple different
     * exit instructions.
     */
    private static final ExitInst FIXED_EXIT_INST
            = new ExitInst();

    /**
     * Constructs an Exit Instruction.
     */
    private ExitInst() {

    }

    /**
     * Produces an Exit Instruction.
     *
     * @return the Exit Instruction.
     */
    public static ExitInst of() {
        return FIXED_EXIT_INST;
    }

    /**
     * Does nothing.
     *
     * @param taskList the taskList to modify.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) throws NoSuchTaskException {
        return "";
    }
}
