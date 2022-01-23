package instructions;

/**
 * This class represents an exit instruction.
 * Format: "bye"
 *
 * @author Ong Han Yang
 */
public class ExitInst extends Instruction {
    /**
     * Use a fixed/static instruction as there are no differences between
     * multiple different exit instructions.
     */
    private static ExitInst FIXED_EXIT_INST
            = new ExitInst();

    /**
     * Private default constructor for an Exit Instruction.
     */
    private ExitInst() {

    }

    /**
     * Factory method to produce an Exit Instruction.
     *
     * @return the Exit Instruction
     */
    public static ExitInst of() {
        return FIXED_EXIT_INST;
    }
}
