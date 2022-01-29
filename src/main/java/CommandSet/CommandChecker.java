package CommandSet;

/**
 * This file contains the implementation of CommandChecker.
 * @author Saravanan Anuja Harish
 */
import Exceptions.InvalidYesOrNoException;
import Exceptions.MissingTaskArgumentException;
import Exceptions.IllegalCommandException;

public class CommandChecker {

    // space
    private static final String SPACE = " ";

    // starting index of a list or a char in string.
    private static final int START_INDEX = 0;

    // MARK stores the command mark.
    private static final String MARK = "mark";

    // UNMARK stores the command unmark.
    private static final String UNMARK = "unmark";

    // stores the command for due before.
    private static final String DUE_BEFORE = "due-before";

    // stores the command for due on.
    private static final String DUE_ON = "due-on";

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

    // LIST variable store the list command
    private static final String LIST = "LIST";

    /**
     * finds and checks if the user command is valid.
     * @param message the user input message.
     * @return the respective command enum.
     */
    public static Commands findAndCheck(String message) {

        try {
            // Checks if the user wants to exit.
            if (message.equalsIgnoreCase(BYE)) {
                return Commands.BYE;
            }

            // Checks if the user wants to display the tasks
            if (message.equalsIgnoreCase(LIST)) {
                return Commands.LIST;
            }

            // Primary error handling for commands.
            if (!message.contains(SPACE)) {
                // check if the commands are empty
                // or else its invalid command
                if (message.equalsIgnoreCase(TODO)) {
                    throw new MissingTaskArgumentException(TODO);
                } else if (message.equalsIgnoreCase(DEADLINE)) {
                    throw new MissingTaskArgumentException(DEADLINE);
                } else if (message.equalsIgnoreCase(EVENT)) {
                    throw new MissingTaskArgumentException(EVENT);
                } else if (message.equalsIgnoreCase(MARK)) {
                    throw new MissingTaskArgumentException(MARK);
                } else if (message.equalsIgnoreCase(UNMARK)) {
                    throw new MissingTaskArgumentException(UNMARK);
                } else if (message.equalsIgnoreCase(DELETE)) {
                    throw new MissingTaskArgumentException(DELETE);
                } else if (message.equalsIgnoreCase(DUE_BEFORE)) {
                    throw new MissingTaskArgumentException(DUE_BEFORE);
                } else if (message.equalsIgnoreCase(DUE_ON)) {
                    throw new MissingTaskArgumentException(DUE_ON);
                } else {
                    throw new IllegalCommandException(message);
                }
            }

            String command = message.split(SPACE, 2)[START_INDEX].toLowerCase();

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
            }

            return Commands.ADD;
        } catch (IllegalCommandException e) {
            System.out.println(e.toString());
            System.out.println("Please try again");
            return Commands.INVALID;
        }
    }

    /**
     * checks whether the user wants to continue from previous tasks.
     * @param message the user input.
     * @return either YES or NO.
     * @throws InvalidYesOrNoException if the user inputs a command other than yes or no.
     */
    public static Commands yesOrNoChecker(String message) {
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
