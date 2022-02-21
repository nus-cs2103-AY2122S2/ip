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
     * Parse the input that has been entered
     * and return the command parsed.
     *
     * @param input the user entered.
     *
     * @return the command that has been parsed.
     */
    public static Commands parse(String input) {
        //Split the strings based on the whitespace to make identifying the command easier.
        String[] stringIntoParts = input.split(" ");
        //The firstWord variable will store the command.
        String firstWord = stringIntoParts[0];
        //Switch determines which command it is and create a command object accordingly.
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
