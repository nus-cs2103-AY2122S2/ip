public class Parser {

    public CommandType parseCommand(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Meow! Enter a valid command!");
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
            throw new DukeException("Meow? I don't know what that means.");
        }
    }

    public String[] parseInput(String input, CommandType command) throws DukeException {
        String[] args = new String[2];
        args[0] = (input + " ").split(" ", 2)[1].trim(); // description
        if (command.equals(CommandType.DELETE) || command.equals(CommandType.MARK)
                || command.equals(CommandType.UNMARK)) {
            if (args[0].isBlank()) {
                throw new DukeException("Meow! Enter a valid task!");
            }
        } else if (command.equals(CommandType.DEADLINE) || command.equals(CommandType.EVENT)
                || command.equals(CommandType.TODO)) {
            if (args[0].isBlank()) {
                throw new DukeException("Meow! A task needs a description!");
            }
            if (command.equals(CommandType.DEADLINE)) {
                if (!input.contains("/by")) {
                    throw new DukeException("Meow! A date is required!");
                }
                args[1] = args[0].split("/by", 2)[1].trim();
                args[0] = args[0].split("/by", 2)[0].trim();
                if (args[1].isBlank()) {
                    throw new DukeException("Meow! A date is required!");
                }
            } else if (command.equals(CommandType.EVENT)) {
                if (!input.contains("/at")) {
                    throw new DukeException("Meow! A date is required!");
                }
                args[1] = args[0].split("/at", 2)[1].trim();
                args[0] = args[0].split("/at", 2)[0].trim();
                if (args[1].isBlank()) {
                    throw new DukeException("Meow! A date is required!");
                }
            }
        }
        return args;
    }
}
