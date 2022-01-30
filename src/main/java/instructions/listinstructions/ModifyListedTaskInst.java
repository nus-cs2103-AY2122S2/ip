package instructions.listinstructions;

import exceptions.InvalidInputException;
import instructions.Instruction;

/**
 * This class encapsulates a Modify Listed Task Instruction.
 * These instructions include these subclasses/instructions:
 * - Mark
 * - Unmark
 * - Delete
 *
 * @author Ong Han Yang
 */
public abstract class ModifyListedTaskInst extends Instruction {
    /** Reusable Invalid Input Exception for when there is no provided task number */
    private static final InvalidInputException NO_TASK_NUM_EXCEPTION =
            new InvalidInputException("Cannot delete without a specified task number!");

    /** Reusable Invalid Input Exception for when the provided task number is not an integer */
    private static final InvalidInputException NOT_INTEGER_EXCEPTION =
            new InvalidInputException("Given task number is not an integer!");

    /** The taskNumber to modify */
    private int taskNum;

    /**
     * Constructs a Modify Listed Task Instruction.
     *
     * @param taskNum the task number to delete.
     */
    protected ModifyListedTaskInst(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Produces a Modify Listed Task Instruction, according to the specified instruction type.
     *
     * @param input the original command called.
     * @return the Modify Listed Task Instruction with the specified task number to delete.
     * @throws InvalidInputException when no task number is provided, or the provided task number
     *          is not an integer.
     */
    public static ModifyListedTaskInst of(String input) throws InvalidInputException {

        // for task number validation
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw NO_TASK_NUM_EXCEPTION;
        }
        String taskNum = split[1].strip();
        if (taskNum.length() == 0) {
            throw NO_TASK_NUM_EXCEPTION;
        }
        int taskNumInteger;
        try {
            taskNumInteger = Integer.parseInt(taskNum);
        } catch (NumberFormatException e) {
            throw NOT_INTEGER_EXCEPTION;
        }

        // for splitting into individual instruction
        String instType = split[0];
        switch (instType) {
        case "mark":
            return MarkAsDoneInst.of(taskNumInteger);
        case "unmark":
            return UnmarkInst.of(taskNumInteger);
        case "delete":
            return DeleteInst.of(taskNumInteger);
        default:
            // will not reach here, as Instruction.of controls the main flow
            // of instructions
            return null;
        }
    }

    /**
     * Gets the task number to modify.
     *
     * @return the task number as an int.
     */
    public int getTaskNum() {
        return this.taskNum;
    }
}
