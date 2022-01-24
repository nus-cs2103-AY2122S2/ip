import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private final Scanner input;

    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");

    /**
     * Available command, their syntax (in regex), and usage documentation
     *
     * NOTE: syntax convention should as below
     * Semantics: command-name arg [[/key kwargs] ... ]
     * Regex: command-name (.*) (/([.+]) (.*))*
     */
    private static final String[][] COMMANDS = {
            { "bye", "bye", "bye" },
            { "list", "list", "list" },
            { "mark", "mark (\\d+)", "mark <task number>" },
            { "unmark", "unmark (\\d+)", "unmark <task number>" },
            { "todo", "todo (.*)", "todo <description>" },
            { "deadline",
                    "deadline (.*) /(by) ((?:[1-9]|[12][0-9]|3[01])/(?:[1-9]|1[0-2])/[0-9]{4})",
                    "deadline <description> /by <d/M/yyyy>" },
            { "event",
                    "event (.*) /(at) ((?:[1-9]|[12][0-9]|3[01])/(?:[1-9]|1[0-2])/[0-9]{4})",
                    "event <description> /at <d/M/yyyy>" },
            { "delete", "delete (\\d+)", "delete <task number>" },
    };

    public CommandParser(Scanner input) {
       this.input = input;
    }

    /**
     * Checks whether a command matches
     *
     * @param cmd command to check validity
     * @return validity of the command
     */
    private static Command parseCommand(String cmd) throws DukeException {
        String cmdName = cmd.split(" ")[0];
        for (String[] command : CommandParser.COMMANDS) {
            // Not the target command
            if (!cmdName.equals(command[0])) continue;

            Pattern p = Pattern.compile(command[1]);
            Matcher m = p.matcher(cmd);

            if (m.matches()) {
                if (m.groupCount() > 0) {
                    String arg = m.group(1);
                    HashMap<String, String> kwargs = new HashMap<>();

                    // kwargs start at 2,
                    // The last one is groupCount() - 1, its value being groupCount()
                    for (int i = 2; i < m.groupCount(); i += 2) {
                        kwargs.put(m.group(i), m.group(i + 1));
                    }

                    return new Command(cmdName, arg, kwargs);
                }
                else {
                    return new Command(cmdName);
                }
            } else {
                // Syntax error
                throw new DukeException(String.format(
                        "Woi, that's not how you do it...\n" +
                        "\tUsage: %s",
                        command[2]));
            }
        }

        throw new DukeException("What are you trying to do??");
    }


    /**
     * Reads user input from previously specified input Scanner
     * Parses command if the first token is a valid command (delimiter = " ")
     * Else, treats the whole input line as a command term
     *
     * @return parsed command
     */
    public Command readAndParse() throws DukeException {
        String commandLine = this.input.nextLine();
        Command command = CommandParser.parseCommand(commandLine);
        return command;
    }
}
