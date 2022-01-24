import java.util.Scanner;

public class Parser {
    private static String botCommandWord;
    private static String description;

    public Parser() {
    }

    private static String readInput(String command) {
        int commandIndex = command.indexOf(" ");
        if (commandIndex == -1) {
            botCommandWord = command;
            description = command;
        } else {
            botCommandWord = command.substring(0, commandIndex);
            description = command.substring(commandIndex + 1);
        }
        return botCommandWord;
    }


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
