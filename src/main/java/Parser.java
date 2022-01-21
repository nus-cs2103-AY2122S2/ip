public class Parser {

    public static CommandType parseCommand(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INPUT);
        }
        String command = (input + " ").split(" ")[0];
        switch (command) {
        case "list":
            return CommandType.LIST;
        case "delete":
            return CommandType.DELETE;
        case "mark":
            return CommandType.MARK;
        case "unmark":
            return CommandType.UNMARK;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "todo":
            return CommandType.TODO;
        default:
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public static String[] parseInput(String input, CommandType command) throws DukeException {
        String[] args = new String[2];
        args[0] = (input + " ").split(" ", 2)[1].trim(); // description
        if (command.equals(CommandType.DELETE) || command.equals(CommandType.MARK)
                || command.equals(CommandType.UNMARK)) {
            if (args[0].isBlank()) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_TASK);
            }
        } else if (command.equals(CommandType.DEADLINE) || command.equals(CommandType.EVENT)
                || command.equals(CommandType.TODO)) {
            if (args[0].isBlank()) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            if (command.equals(CommandType.DEADLINE)) {
                if (!input.contains("/by")) {
                    throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
                }
                args[1] = args[0].split("/by", 2)[1].trim();
                args[0] = args[0].split("/by", 2)[0].trim();
                if (args[1].isBlank()) {
                    throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
                }
            } else if (command.equals(CommandType.EVENT)) {
                if (!input.contains("/at")) {
                    throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
                }
                args[1] = args[0].split("/at", 2)[1].trim();
                args[0] = args[0].split("/at", 2)[0].trim();
                if (args[1].isBlank()) {
                    throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
                }
            }
        }
        return args;
    }
}
