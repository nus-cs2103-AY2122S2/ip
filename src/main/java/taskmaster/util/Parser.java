package taskmaster.util;
import taskmaster.commands.AddCommands;
import taskmaster.commands.ByeCommands;
import taskmaster.commands.Commands;
import taskmaster.commands.DefaultCommands;
import taskmaster.commands.DeleteCommands;
import taskmaster.commands.FindCommands;
import taskmaster.commands.HelpCommands;
import taskmaster.commands.ListCommands;
import taskmaster.commands.MarkCommands;


/**
 * This class encapsulates a Parser Object which parses text input into a command.
 */
public class Parser {
    /**
     * Method to help user perform the task that has been
     * entered based on the input.
     *
     * @param input Input the user entered.
     */
    public static Commands parse(String input) {
        String[] stringIntoParts = input.split(" ");
        String firstWord = stringIntoParts[0];
        switch (firstWord) {
        case "list":
            return new ListCommands();
        case "mark": case "unmark":
            return new MarkCommands(input);
        case "delete":
            return new DeleteCommands(input);
        case "todo": case "deadline": case "event":
            return new AddCommands(input);
        case "find":
            return new FindCommands(input);
        case "bye":
            return new ByeCommands();
        case "help":
            return new HelpCommands();
        default:
            return new DefaultCommands(input);
        }
    }
}
