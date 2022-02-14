package apollo.commands;

/**
 * Prints help for available commands.
 * Extends {@code Command} superclass.
 */
public class HelpCommand extends Command {

    private final String command;
    private final String header = "Purpose of command: usage\n";


    /**
     * Comstructor for {@code HelpCommand}.
     *
     * @param arguments Arguments from parser.
     */
    public HelpCommand(String[] arguments) {
        if (arguments.length == 2) {
            command = arguments[1].split(" ")[0].trim().toLowerCase();
        } else {
            command = "";
        }
    }

    private String showHelp() {
        return "Apollo commands help:" + "\n"
                + header + "\n"
                + AddCommand.HELP_ADD_TODO + "\n"
                + AddCommand.HELP_ADD_DEADLINE + "\n"
                + AddCommand.HELP_ADD_EVENT + "\n"
                + DeleteCommand.HELP_DELETE_COMMAND + "\n"
                + FindCommand.HELP_FIND_COMMAND + "\n"
                + ListCommand.HELP_LIST_COMMAND + "\n"
                + MarkCommand.HELP_MARK_COMMAND + "\n"
                + ExitCommand.HELP_EXIT_COMMAND;
    }

    @Override
    public String execute() {
        switch (command) {
        case "add": case "todo": case "deadline": case "event":
            return header + AddCommand.HELP_ADD_COMMAND;
        case "delete":
            return header + DeleteCommand.HELP_DELETE_COMMAND;
        case "find":
            return header + FindCommand.HELP_FIND_COMMAND;
        case "list":
            return header + ListCommand.HELP_LIST_COMMAND;
        case "mark": case "unmark":
            return header + MarkCommand.HELP_MARK_COMMAND;
        case "exit":
            return header + ExitCommand.HELP_EXIT_COMMAND;
        default:
            return showHelp();
        }
    }
}
