package instructions._new.task.instructions;

import Exceptions.InvalidInputException;
import instructions.Instruction;

/**
 * This class represents a New Task Instruction, to create a new task to be
 * added in the taskList.
 * These instructions include these subclasses/instructions:
 * - To Do
 * - Deadline
 * - Event
 *
 * @author Ong Han Yang
 */
public abstract class NewTaskInst extends Instruction {
    /** Reusable Invalid Input Exception for when there is no provided task
     * number */
    protected static InvalidInputException NO_TASK_DETAILS
            = new InvalidInputException("No task details is provided!");

    /** The provided description for the task to be added */
    private String taskDesc;

    /**
     * Constructs a New Task Instruction.
     *
     * @param taskDesc the description to be included in the new Task.
     */
    protected NewTaskInst(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    /**
     /**
     * Factory method to produce a New Task Instruction, according to the
     * specified instruction type.
     *
     * @param instType the type of new task instruction to use.
     * @param taskDetails the task number to modify, as a String.
     * @return the Modify Listed Task Instruction with the specified task number
     *         to delete.
     * @throws InvalidInputException when no task number is provided, or the provided task number
     *      is not an integer.
     */
    public static NewTaskInst of(String instType, String taskDetails) throws InvalidInputException {
        if (taskDetails.length() == 0) {
            throw NO_TASK_DETAILS;
        }
        switch (instType) {
        case "todo":
            return TodoInst.of(taskDetails);
        case "deadline":
            return DeadlineInst.of(taskDetails);
        case "event":
            return Event.of(taskDetails);
        default:
            // will not reach here, as Instruction.of controls the main flow of instructions
            return null;
        }
    }
}
