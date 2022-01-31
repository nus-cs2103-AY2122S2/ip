package instructions;

import tasks.TaskList;

/**
 * This class represents an Hello instruction.
 * <br>
 * Format: {@code hi}
 *
 * @author Ong Han Yang
 */
public class HelloInst extends Instruction {
    /**
     * Use a fixed/static instruction as there are no differences between multiple different
     * hello instructions.
     */
    private static final HelloInst FIXED_HELLO_INST =
            new HelloInst();

    /**
     * Constructs an Hello Instruction.
     */
    private HelloInst() {

    }

    /**
     * Produces a Hello Instruction.
     *
     * @return the Hello Instruction.
     */
    public static HelloInst of() {
        return FIXED_HELLO_INST;
    }

    /**
     * Does nothing.
     *
     * @param taskList the taskList to modify.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) {
        return "";
    }
}
