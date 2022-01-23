package instructions;

/**
 * This class represents a display list instruction.
 * Format: "list"
 *
 * @author Ong Han Yang
 */
public class DisplayListInst extends Instruction {
    /**
     * A fixed/static display instruction as there are no differences between multiple
     * display list instructions.
     * TODO:
     * Maybe allow a "list x", to list up to the first x tasks of the list.
     */
    private static DisplayListInst FIXED_DISPLAY_INST
            = new DisplayListInst();

    /**
     * Private default constructor for a Display List Instruction.
     */
    private DisplayListInst() {
    }

    /**
     * Factory method to produce a Display List Instruction.
     *
     * @return the Display List Instruction.
     */
    public static DisplayListInst of() {
        return FIXED_DISPLAY_INST;
    }
}
