package instructions.modify.listed.task.instructions;

import Exceptions.InvalidInputException;
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
    protected static InvalidInputException NO_TASK_NUM
            = new InvalidInputException("Cannot delete without a specified task number!");

    /** Reusable Invalid Input Exception for when the provided task number is not an integer */
    protected static InvalidInputException NOT_INTEGER
            = new InvalidInputException("Given task number is not an integer!");

    /** The taskNumber to modify */
    private int taskNum;

    /**
     * Private constructor for a Modify Listed Task Instruction.
     *
     * @param taskNum the task number to delete.
     */
    protected ModifyListedTaskInst(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Factory method to produce a Modify Listed Task Instruction, according to the specified instruction
     * type.
     *
     * @param instType the type of modify listed task instruction to use.
     * @param taskNum the task number to modify, as a String.
     * @return the Modify Listed Task Instruction with the specified task number to delete.
     * @throws InvalidInputException when no task number is provided, or the provided task number
     *      is not an integer.
     */
    public static ModifyListedTaskInst of(String instType, String taskNum) throws InvalidInputException {
        if (taskNum.length() == 0) {
            throw NO_TASK_NUM;
        }
        int taskNumInteger;
        try {
            taskNumInteger  = Integer.parseInt(taskNum);
        } catch (NumberFormatException e) {
            throw NOT_INTEGER;
        }
        switch (instType) {
        case "mark":
            return MarkAsDoneInst.of(taskNumInteger);
        case "unmark":
            return UnmarkInst.of(taskNumInteger);
        case "delete":
            return DeleteInst.of(taskNumInteger);
        default:
            // will not reach here, as Instruction.of controls the main flow of instructions
            return null;
        }
    }

    /**
     * Getter for the task number to delete.
     *
     * @return the task number as an int.
     */
    public int getTaskNum() {
        return this.taskNum;
    }
}
