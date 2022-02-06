package duke;

/**
 * Represents a <code>Parser</code> which parses input strings
 * to <code>Duke</code> and returns the appropriate enum type
 * defining the command.
 */


public class Parser {

    public Parser() {}

    /**
     * Parses input string to return appropriate enum type.
     * @param input
     * @return The appropriate enum type.
     */
    public CommandType parse(String input) {
        assert !input.isEmpty() : "Given input should not be empty";
        if (input.startsWith("bye")) {
            return CommandType.BYE;
        } else if (input.startsWith("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (input.startsWith("event")){
            return CommandType.EVENT;
        } else if (input.startsWith("find")) {
            return CommandType.FIND;
        } else if (input.startsWith("update")) {
            return CommandType.UPDATE;
        } else {
            System.out.println("Invalid command");
            return null;
        }
    }


}
