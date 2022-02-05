package duke;

/**
 * Class that parses user input and returns
 * command type of input
 */
public class Parser {

    /**
     * Reads user input and returns command type detected
     *
     * @param input User input text
     * @return Command type
     */
    public static Command parse(String input) {
        String[] inputSplit = input.split(" ");

        if (inputSplit.length > 0) {
            String commandString = inputSplit[0];

            if (commandString.equals("bye")) {
                return Command.BYE;
            } else if (commandString.equals("list")) {
                return Command.LIST;
            } else if (commandString.equals("mark")) {
                return Command.MARK;
            } else if (commandString.equals("unmark")) {
                return Command.UNMARK;
            } else if (commandString.equals("deadline")) {
                return Command.DEADLINE;
            } else if (commandString.equals("event")) {
                return Command.EVENT;
            } else if (commandString.equals("todo")) {
                return Command.TODO;
            } else if (commandString.equals("delete")) {
                return Command.DELETE;
            } else if (commandString.equals("find")) {
                return Command.FIND;
            } else {
                return Command.ERROR;
            }
        } else {
            return Command.ERROR;
        }
    }
}
