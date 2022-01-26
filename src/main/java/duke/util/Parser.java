package duke.util;

import duke.command.*;
import duke.datetime.DateTable;

/**
 * Helps to parse the input command into the respective Command Object
 */
public class Parser {
    private static String botCommandWord = "";
    private static String description = "";

    public Parser() {
    }

    /**
     * Reads the input command and separates into different components
     *
     * @param command User input command
     */
    private static void readInput(String command) {
        int commandIndex = command.indexOf(" ");
        botCommandWord = separateCommandWord(command, commandIndex);
        description = separateDescription(command, commandIndex);
    }

    /**
     * Returns the key command word from the input command
     *
     * @param command      The user input command
     * @param commandIndex Separation index between key command word and description
     * @return The key command word
     */
    private static String separateCommandWord(String command, int commandIndex) {
        if (commandIndex == -1) {
            return command;
        } else {
            return command.substring(0, commandIndex);
        }
    }

    /**
     * Returns the description from the input command
     *
     * @param command      The user input command
     * @param commandIndex Separation index between key command word and description
     * @return The description
     */
    private static String separateDescription(String command, int commandIndex) {
        if (commandIndex == -1) {
            return command;
        } else {
            return command.substring(commandIndex + 1);
        }
    }

    /**
     * Returns a specific Command Object base on the input command
     *
     * @param dateTable The database of date and tasks
     * @param fullInput The user input command
     * @return Command Object that suitable for the input command
     */
    public static Command parse(DateTable dateTable, String fullInput) {
        readInput(fullInput);
        switch (botCommandWord) {
        case "list":
            return new ShowListCommand();
        case "mark":
            return new UpdateMarkCommand(description, true);
        case "unmark":
            return new UpdateMarkCommand(description, false);
        case "todo":
            return new AddCommand(fullInput, description, "T");
        case "deadline":
            return new AddCommand(fullInput, description, "D");
        case "event":
            return new AddCommand(fullInput, description, "E");
        case "delete":
            return new DeleteCommand(description);
        case "date":
            return new DateCommand(dateTable, description);
        case "bye":
            return new ByeCommand();
        default:
            return new WrongSyntaxCommand();
        }
    }
}
