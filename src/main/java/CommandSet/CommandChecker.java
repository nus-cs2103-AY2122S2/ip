package commandset;

import exceptions.IllegalCommandException;
import exceptions.InvalidYesOrNoException;
import exceptions.MissingTaskArgumentException;
import helper.Ui;

/**
 * <h1>CommandChecker</h1>
 * <p>
 * CommandChecker class checks the user input and returns the type of command input by the user.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class CommandChecker {

    // stores space.
    private static final String SPACE = " ";

    // starting index of a list or a char in string.
    private static final int START_INDEX = 0;

    // Stores the split limit.
    private static final int SPLIT_LIMIT = 2;

    // MARK stores the command mark.
    private static final String MARK = "mark";

    // UNMARK stores the command unmark.
    private static final String UNMARK = "unmark";

    // stores the command for due before.
    private static final String DUE_BEFORE = "due-before";

    // stores the command for due on.
    private static final String DUE_ON = "due-on";

    // stores the command for find.
    private static final String FIND = "find";

    // Stores the command TODO.
    private static final String TODO = "TODO";

    // Stores the command DEADLINE.
    private static final String DEADLINE = "DEADLINE";

    // Stores the command EVENT.
    private static final String EVENT = "EVENT";

    // BYE variable stores bye, which recognises user exit command.
    private static final String BYE = "BYE";

    //DELETE variable stores delete command.
    private static final String DELETE = "delete";

    // the user input when they want to continue with the previous task list.
    private static final String YES = "yes";

    // the user input when they don't want to continue with the previous task list.
    private static final String NO = "no";

    // LIST variable store the list command.
    private static final String LIST = "LIST";

    /**
     * finds and checks if the user command is valid.
     *
     * @param message the user input message.
     * @return the respective command enum.
     */
    public static Commands findAndCheck(String message) {

        try {
            // Checks if the user wants to exit.
            if (message.equalsIgnoreCase(BYE)) {
                return Commands.BYE;
            }

            // Checks if the user wants to display the tasks.
            if (message.equalsIgnoreCase(LIST)) {
                return Commands.LIST;
            }

            // Primary error handling for commands.
            if (!message.contains(SPACE)) {
                throwMissingTaskArgument(message);
            }

            assert message.contains(SPACE) : "wrong format of command";
            String command = message.split(SPACE, SPLIT_LIMIT)[START_INDEX].toLowerCase();
            return findCommandType(command);            

        } catch (IllegalCommandException e) {
            Ui.printMessage(e.toString() + "\nPlease try again!");
            return Commands.INVALID;
        }
    }

    /**
     * throws an exception depending on the command type.
     *
     * @param command the command entered by the user.
     * @throws MissingTaskArgumentException if the command is missing the task argument.
     * @throws IllegalCommandException if the command is not appropriate.
     */
    private static void throwMissingTaskArgument(String command) {

        // check if the commands are empty,
        // or else its invalid command.
        if (command.equalsIgnoreCase(TODO)) {
            throw new MissingTaskArgumentException(TODO);
        } else if (command.equalsIgnoreCase(DEADLINE)) {
            throw new MissingTaskArgumentException(DEADLINE);
        } else if (command.equalsIgnoreCase(EVENT)) {
            throw new MissingTaskArgumentException(EVENT);
        } else if (command.equalsIgnoreCase(MARK)) {
            throw new MissingTaskArgumentException(MARK);
        } else if (command.equalsIgnoreCase(UNMARK)) {
            throw new MissingTaskArgumentException(UNMARK);
        } else if (command.equalsIgnoreCase(DELETE)) {
            throw new MissingTaskArgumentException(DELETE);
        } else if (command.equalsIgnoreCase(DUE_BEFORE)) {
            throw new MissingTaskArgumentException(DUE_BEFORE);
        } else if (command.equalsIgnoreCase(DUE_ON)) {
            throw new MissingTaskArgumentException(DUE_ON);
        } else if (command.equalsIgnoreCase(FIND)) {
            throw new MissingTaskArgumentException(FIND);
        } else {
            throw new IllegalCommandException(command);
        }
    }

    /**
     * checks the command type.
     *
     * @param command the command entered by the user.
     * @return command type entered by the user..
     */
    private static Commands findCommandType(String command) {
        // check if the command says mark or unmarked.
        switch (command) {
        case MARK:
            return Commands.MARK;
        case UNMARK:
            return Commands.UNMARK;
        case DELETE:
            return Commands.DELETE;
        case DUE_BEFORE:
            return Commands.DUEBEFORE;
        case DUE_ON:
            return Commands.DUEON;
        case FIND:
            return Commands.FIND;
        default:
            return Commands.ADD;
        }
    }

    /**
     * checks whether the user wants to continue from previous tasks.
     *
     * @param message the user input.
     * @return either YES or NO.
     * @throws InvalidYesOrNoException if the user inputs a command other than yes or no.
     */
    public static Commands checkYesOrNo(String message) {
        switch (message.toLowerCase()) {
        case YES:
            return Commands.YES;
        case NO:
            return Commands.NO;
        default:
            throw new InvalidYesOrNoException(message);
        }
    }

}
