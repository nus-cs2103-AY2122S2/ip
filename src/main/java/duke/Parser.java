package duke;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.QuitCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.InvalidCommandException;

public class Parser {
    private static final String QUIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String HELP_COMMAND = "help";

    /**
     * Parse the user command.
     *
     * @param cmdString string command
     * @return result after executing the command
     */
    public static Command parse(String cmdString) throws InvalidCommandException {
        String[] argv = getArgs(cmdString);
        String action = getAction(argv);
        Command cmd;

        switch (action) {
        case QUIT_COMMAND:
            cmd = new QuitCommand();
            break;
        case LIST_COMMAND:
            cmd = new ListCommand();
            break;
        case MARK_COMMAND:
            cmd = new MarkCommand(getIdx(argv));
            break;
        case UNMARK_COMMAND:
            cmd = new UnmarkCommand(getIdx(argv));
            break;
        case DELETE_COMMAND:
            cmd = new DeleteCommand(getIdx(argv));
            break;
        case FIND_COMMAND:
            cmd = new FindCommand(getKeyword(argv));
            break;
        case HELP_COMMAND:
            cmd = new HelpCommand();
            break;
        default:
            cmd = new AddCommand(cmdString);
            break;
        }
        return cmd;
    }

    /**
     * Get the arguments from the command string.
     *
     * @param cmdString command
     * @return array of args
     */
    public static String[] getArgs(String cmdString) {
        return cmdString.split(" ", 2);
    }

    /**
     * Get the action from the command string.
     *
     * @param argv array of args
     * @return action
     * @throws InvalidCommandException command is invalid
     */
    public static String getAction(String[] argv) throws InvalidCommandException {
        if (argv[0].equals("")) {
            throw new InvalidCommandException("No command specified");
        }

        return argv[0];
    }

    /**
     * Get index from the args.
     *
     * @param argv array of args
     * @return index
     * @throws InvalidCommandException index is invalid
     */
    public static int getIdx(String[] argv) throws InvalidCommandException {
        if (argv.length < 2) {
            throw new InvalidCommandException("No index specified");
        }
        if (!isNumeric(argv[1])) {
            throw new InvalidCommandException("Invalid index");
        }

        return Integer.parseInt(argv[1]);
    }

    /**
     * Get keyword from the args.
     *
     * @param argv array of args
     * @return keyword
     * @throws InvalidCommandException keyword is invalid
     */
    public static String getKeyword(String[] argv) throws InvalidCommandException {
        if (argv.length < 2) {
            throw new InvalidCommandException("No keyword specified");
        }

        return argv[1];
    }

    /**
     * Checks if the string is numeric.
     *
     * @param string raw string
     * @return whether the string is numeric
     */
    public static boolean isNumeric(String string) {
        if (string == null || string.equals("")) {
            return false;
        }

        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
