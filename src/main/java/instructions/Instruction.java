package instructions;

import exceptions.InvalidActionException;
import exceptions.InvalidInputException;
import exceptions.NoSuchTaskException;
import instructions.taskinstructions.NewTaskInst;
import instructions.taskinstructions.listinstructions.DisplayListInst;
import instructions.taskinstructions.listinstructions.FindInst;
import instructions.taskinstructions.listinstructions.ModifyListedTaskInst;
import tasks.TaskList;

/**
 * This class represents an instruction given to the chatbot.
 *
 * INSPIRATION was taken from AinsleyJ's code, where he separated individual
 * instructions into classes.
 *
 * @author Ong Han Yang
 */
public abstract class Instruction {
    /** Reusable Invalid Input Exception for when an unknown input is given to create an instruction */
    private static final InvalidInputException UNKNOWN_INPUT =
            new InvalidInputException("I don't understand what you said!");

    /**
     * Default constructor that takes in no arguments.
     * This is to allow inheritance from subclasses.
     */
    protected Instruction() {

    }

    /**
     * Produces an instruction given a String input.
     *
     * @param input the String input.
     * @return the Instruction that matches the String input.
     */
    public static Instruction of(String input) throws InvalidInputException {
        String[] split = input.split(" ", 2);
        String instType = split[0];
        switch (instType) {
        case "list":
            return DisplayListInst.of();
        case "hi":
            return HelloInst.of();
        case "bye":
            return ExitInst.of();
        case "mark": // Fallthrough
        case "unmark": // Fallthrough
        case "delete":
            return ModifyListedTaskInst.of(input);
        case "todo": // Fallthrough
        case "deadline": // Fallthrough
        case "event": // Fallthrough
        case "do":
            return NewTaskInst.of(input);
        case "find":
            return FindInst.of(input);
        default:
            throw UNKNOWN_INPUT;
        }
    }

    /**
     * Performs the instruction's instruction. Some instructions may need a task list.
     *
     * @param taskList the task list for the instruction to perform its action on.
     * @return a String feedback onto the state of the instruction.
     * @throws NoSuchTaskException when the instruction is a ModifyListedTaskInstruction and there
     *          is no specified task number.
     * @throws InvalidActionException when the user tries to mark/unmark an already marked/unmarked
     *          instruction.
     */
    public abstract String doInst(TaskList taskList) throws NoSuchTaskException, InvalidActionException;
}
