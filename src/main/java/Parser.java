public class Parser {

    public Parser() {}

    public CommandType parse(String input) {
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
        } else {
            return CommandType.EVENT;
        }
    }


}
