package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.DeleteCommand;
import duke.command.ShowListCommand;
import duke.command.UpdateMarkCommand;
import duke.command.WrongSyntaxCommand;
import duke.datetime.DateTable;

/**
 * Helps to parse the input command into the respective Command Object
 */
public class Parser {
    private static String botCommandWord;
    private static String description;

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
     * @param command The user input command
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
     * @param command The user input command
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
     * @param fullCommand The user input command
     * @return Command Object that suitable for the input command
     */
    public static Command parse(DateTable dateTable, String fullCommand) {
        readInput(fullCommand);
        switch (botCommandWord) {
        case "list":
            return new ShowListCommand();
        case "mark":
            return new UpdateMarkCommand(description, true);
        case "unmark":
            return new UpdateMarkCommand(description, false);
        case "todo":
            return new AddCommand(fullCommand, description,"T");
        case "deadline":
            return new AddCommand(fullCommand, description,"D");
        case "event":
            return new AddCommand(fullCommand, description,"E");
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
