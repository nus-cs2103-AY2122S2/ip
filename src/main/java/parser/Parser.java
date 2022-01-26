package parser;

import command.*;

public class Parser {
    public enum Commands {
        TODO,
        DEADLINE,
        EVENT,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        BYE;
    }

    public static Command parse(String fullCommand) {
        String response = fullCommand.trim();
        if (response.equals("")) {
            return new IncorrectCommand();
        }
        String[] responseArray = response.split("\\s+"), secondSplit;
        Commands command;
        if (responseArray.length > 0) {
            try {
                command = Commands.valueOf(responseArray[0].toUpperCase());
                switch (command) {
                    case TODO:
                        return new TodoCommand(removeSubString(response, "todo "));
                    case DEADLINE:
                        secondSplit = response.split(" /by ");
                        return new DeadlineCommand(removeSubString(response, "deadline "), secondSplit[1]);
                    case EVENT:
                        secondSplit = response.split(" /at ");
                        return new EventCommand(removeSubString(response, "event "), secondSplit[1]);
                    case MARK:
                        return new MarkCommand(Integer.parseInt(responseArray[1]));
                    case UNMARK:
                        return new UnmarkCommand(Integer.parseInt(responseArray[1]));
                    case DELETE:
                        return new DeleteCommand(Integer.parseInt(responseArray[1]));
                    case BYE:
                        return new ExitCommand();
                    case LIST:
                        return new ListCommand();
                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                return new IncorrectCommand();
            }
        }
        return new IncorrectCommand();
    }

    public static String removeSubString(String response, String word) {
        return response.replace(word, "");
    }
}
