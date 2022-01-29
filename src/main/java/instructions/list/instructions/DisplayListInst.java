package instructions.list.instructions;

import exceptions.NoSuchTaskException;
import instructions.Instruction;
import tasks.TaskList;

/**
 * This class represents a display list instruction.
 * <br>
 * Format: {@code list}
 *
 * @author Ong Han Yang
 */
public class DisplayListInst extends Instruction {
    /**
     * A fixed/static display instruction as there are no differences between multiple display
     * list instructions.
     * TODO:
     * Maybe allow a "list x", to list up to the first x tasks of the list.
     */
    private static DisplayListInst FIXED_DISPLAY_INST
            = new DisplayListInst();

    /**
     * Constructs a Display List Instruction.
     */
    private DisplayListInst() {
    }

    /**
     * Produces a Display List Instruction.
     *
     * @return the Display List Instruction.
     */
    public static DisplayListInst of() {
        return FIXED_DISPLAY_INST;
    }

    /**
     * Displays the taskList as a string.
     *
     * @param taskList the taskList to show.
     * @return the feedback message after performing this instruction.
     */
    @Override
    public String doInst(TaskList taskList) throws NoSuchTaskException {
        return taskList.toString();
    }
}
