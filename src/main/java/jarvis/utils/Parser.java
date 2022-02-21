package jarvis.utils;

import jarvis.commands.AddCommand;
import jarvis.commands.Command;
import jarvis.commands.DeleteCommand;
import jarvis.commands.FindCommand;
import jarvis.commands.HelpCommand;
import jarvis.commands.ListCommand;
import jarvis.commands.MarkCommand;
import jarvis.commands.QuitCommand;
import jarvis.commands.TagCommand;
import jarvis.commands.TagsCommand;
import jarvis.commands.TaskTagsCommand;
import jarvis.commands.UnmarkCommand;
import jarvis.commands.UntagCommand;
import jarvis.exceptions.InvalidCommandException;
import jarvis.tasks.Task;

public class Parser {
    private static final String QUIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String TAGS_COMMAND = "tags";
    private static final String TASKTAGS_COMMAND = "tasktags";
    private static final String TAG_COMMAND = "tag";
    private static final String UNTAG_COMMAND = "untag";
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
        String[] params;
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
        case TAGS_COMMAND:
            cmd = new TagsCommand();
            break;
        case TASKTAGS_COMMAND:
            cmd = new TaskTagsCommand(getIdx(argv));
            break;
        case TAG_COMMAND:
            params = getParams(argv, 2);
            cmd = new TagCommand(Integer.parseInt(params[0]), params[1]);
            break;
        case UNTAG_COMMAND:
            params = getParams(argv, 2);
            cmd = new UntagCommand(Integer.parseInt(params[0]), params[1]);
            break;
        case Task.TODO:
        case Task.DEADLINE:
        case Task.EVENT:
            cmd = new AddCommand(cmdString);
            break;
        default:
            throw new InvalidCommandException("Invalid command!\nType help to see the list of commands");
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
     * Get params from the args.
     *
     * @param argv array of args
     * @return params
     * @throws InvalidCommandException keyword is invalid
     */
    public static String[] getParams(String[] argv, int argc) throws InvalidCommandException {
        if (argv.length < 2) {
            throw new InvalidCommandException("No params specified");
        } else if (argv.length - 1 < argc) {
            throw new InvalidCommandException(
                String.format("Command requires %d arguments but only %d specified.", argc, argv.length - 1));
        }

        return argv[1].split(" ");
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
