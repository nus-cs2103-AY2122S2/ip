package duke;

/**
 * Represents a parser to format user input
 */
public class Parser {

    /**
     * Returns Command enum of string that user entered
     *
     * @param command String that needs to be converted to Command enum
     * @return Command enum of command to execute
     * @throws CommandNotFoundException when command is not found
     */
    public static Command parseCommand(String command) throws CommandNotFoundException {
        command = command.toLowerCase();
        switch (command) {
        case "list":
            return Command.LIST;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "event":
            return Command.EVENT;
        case "deadline":
            return Command.DEADLINE;
        case "todo":
            return Command.TODO;
        case "bye":
            return Command.BYE;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        default:
            throw new CommandNotFoundException("Unrecognised Command");
        }
    }

    /**
     * Returns String array of formatted input
     * Index 0: Description of task
     * Index 1: Time
     * Time will be an empty string if not provided
     *
     * @param input String to be formatted
     * @return String array of formatted input
     */
    public static String[] parseInput(String input) {
        String[] inputs = new String[2];
        if (input.contains("/by")) {
            int indexOfTime = input.indexOf("/by");
            inputs[0] = input.substring(0, indexOfTime);
            inputs[1] = input.substring(indexOfTime + 4);
        } else if (input.contains("/at")) {
            int indexOfTime = input.indexOf("/at");
            inputs[0] = input.substring(0, indexOfTime);
            inputs[1] = input.substring(indexOfTime + 4);
        } else {
            inputs[0] = input;
            inputs[1] = "";
        }
        return inputs;
    }
}
