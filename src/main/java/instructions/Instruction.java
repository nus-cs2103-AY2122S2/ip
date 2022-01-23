package instructions;

import instructions._new.task.instructions.NewTaskInst;
import Exceptions.InvalidInputException;
import instructions.modify.listed.task.instructions.DeleteInst;
import instructions.modify.listed.task.instructions.MarkAsDoneInst;
import instructions.modify.listed.task.instructions.ModifyListedTaskInst;
import instructions.modify.listed.task.instructions.UnmarkInst;

/**
 * This class represents an instruction given to the chatbot.
 *
 * INSPIRATION was taken from AinsleyJ's code, where he separated individual instructions into classes.
 * @author Ong Han Yang
 */
public class Instruction {
    /** Reusable Invalid Input Exception for when an unknown input is given to create an instruction */
    private static InvalidInputException UNKNOWN_INPUT
            = new InvalidInputException("I don't understand what you said!");

    /**
     * Default constructor that takes in no arguments.
     * This is to allow inheritance from subclasses.
     */
    protected Instruction() {
    }

    /**
     * Factory method to provide an instruction given a String input.
     *
     * @param input the String input.
     * @return the Instruction that matches the String input.
     */
    public static Instruction of(String input) throws InvalidInputException {
        String[] split = input.split(" ", 2);
        String instType = split[0];
        String args = split[1];
        switch (instType) {
        case "list":
            return DisplayListInst.of();
        case "bye":
            return ExitInst.of();
        case "mark":
        case "unmark":
        case "delete":
            return ModifyListedTaskInst.of(instType, args);
        case "todo":
        case "deadline":
        case "event":
            return NewTaskInst.of(instType, args);
        default:
            throw UNKNOWN_INPUT;
        }
    }
}
