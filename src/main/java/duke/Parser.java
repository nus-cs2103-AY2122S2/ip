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
        if (command.equals("list")) {
            return Command.LIST;
        } else if (command.equals("mark")) {
            return Command.MARK;
        } else if (command.equals("unmark")) {
            return Command.UNMARK;
        } else if (command.equals("event")) {
            return Command.EVENT;
        } else if (command.equals("deadline")) {
            return Command.DEADLINE;
        } else if (command.equals("todo")) {
            return Command.TODO;
        } else if (command.equals("bye")) {
            return Command.BYE;
        } else if (command.equals("delete")) {
            return Command.DELETE;
        } else if (command.equals("find")) {
            return Command.FIND;
        } else {
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
        if (input.indexOf("/by") != -1) {
            int indexOfTime = input.indexOf("/by");
            inputs[0] = input.substring(0, indexOfTime);
            inputs[1] = input.substring(indexOfTime + 4);
        } else if (input.indexOf("/at") != -1) {
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
