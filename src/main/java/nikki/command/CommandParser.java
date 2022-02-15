package nikki.command;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nikki.NikkiException;

/**
 * Class to encapsulate methods related to parsing user input
 * Contains available commands and their relative syntax
 */
public class CommandParser {
    /** Date format for parsing from user input, file i/o */
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Available commands, their syntax (in regex), and usage documentation
     *
     * NOTE: syntax convention should as below
     * Semantics: command-name arg [[/key kwargs] ... ]
     * Regex: command-name (.*) (/([.+]) (.*))*
     */
    private static final String[][] COMMANDS = {
        { "bye", "bye", "bye" },
        { "list", "list", "list" },
        { "find", "find (.*)", "find <some string>" },
        { "mark", "mark (\\d+)", "mark <task number>" },
        { "unmark", "unmark (\\d+)", "unmark <task number>" },
        { "todo", "todo (.*)", "todo <description>" },
        {
            "deadline",
            "deadline (.*) /(by) ((?:[1-9]|[12][0-9]|3[01])/(?:[1-9]|1[0-2])/[0-9]{4})",
            "deadline <description> /by <d/M/yyyy>"
        },
        {
            "event",
            "event (.*) /(at) ((?:[1-9]|[12][0-9]|3[01])/(?:[1-9]|1[0-2])/[0-9]{4})",
            "event <description> /at <d/M/yyyy>"
        },
        { "delete", "delete (\\d+)", "delete <task number>" },
    };

    /**
     * Checks whether a command matches
     *
     * @param cmd command to check validity
     * @return validity of the command
     * @throws NikkiException invalid command or syntax
     */
    public static Command parseCommand(String cmd) throws NikkiException {
        String cmdName = cmd.split(" ")[0];
        for (String[] command : CommandParser.COMMANDS) {
            // Not the target command
            if (!cmdName.equals(command[0])) {
                continue;
            }

            Pattern p = Pattern.compile(command[1]);
            Matcher m = p.matcher(cmd);

            // Syntax error
            if (!m.matches()) {
                throw new NikkiException(String.format(
                        "Woi, that's not how you do it...\n"
                                + "\tUsage: %s",
                        command[2]));
            }

            // No args or kwargs
            if (m.groupCount() == 0) {
                return new Command(cmdName);
            }

            // Match args
            String arg = m.group(1);
            HashMap<String, String> kwargs = new HashMap<>();

            // Match kwargs
            for (int i = 2; i < m.groupCount(); i += 2) {
                kwargs.put(m.group(i), m.group(i + 1));
            }

            return new Command(cmdName, arg, kwargs);
        }

        throw new NikkiException("What are you trying to do??");
    }
}
