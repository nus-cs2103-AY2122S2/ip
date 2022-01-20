import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private final Scanner input;

    /**
     * Available command and their syntax (in regex)
     *
     * NOTE: syntax convention should as below
     * Semantics: command-name arg [[/key kwargs] ... ]
     * Regex: command-name (.*) (/([.+]) (.*))*
     */
    private static final String[][] commands = {
            { "bye", "bye" },
            { "list", "list" },
            { "mark", "mark (\\d+)" },
            { "unmark", "unmark (\\d+)" },
            { "todo", "todo (.*)" },
            { "deadline", "deadline (.*) /(by) (.*)" },
            { "event", "event (.*) /(at) (.*)" }
    };

    public CommandParser(Scanner input) {
       this.input = input;
    }

    /**
     * Checks whether a command matches
     *
     * @param cmd Command to check validity
     * @return Validity of the command
     */
    private static Command parseCommand(String cmd) {
        for (String[] command : CommandParser.commands) {
            Pattern p = Pattern.compile(command[1]);
            Matcher m = p.matcher(cmd);

            if (m.matches()) {
                // Parse using this command
                if (m.groupCount() > 0) {
                    String arg = m.group(1);
                    HashMap<String, String> kwargs = new HashMap<>();
                    for (int i = 2; i <= m.groupCount(); i += 2) {
                        // /(key) (kwargs)
                        kwargs.put(m.group(i), m.group(i + 1));
                    }

                    return new Command(command[0], arg, kwargs);
                }
                else {
                    return new Command(command[0]);
                }
            }
        }

        return null;
    }


    /**
     * Reads user input from previously specified input Scanner
     * Parses command if the first token is a valid command (delimiter = " ")
     * Else, treats the whole input line as a command term
     *
     * @return Parsed command
     */
    public Command readAndParse() {
        String commandLine = this.input.nextLine();
        Command command = CommandParser.parseCommand(commandLine);
        return command;
    }
}
