package duke.praser;

import duke.command.Command;
import duke.dukeexceptions.InvalidCommand;

/**
 * The Praser object check for the user input before creating any command.
 */
public class Praser{
    /**
     * Checks the user input before either returning a command or throwing an error.
     *
     * @param userInput The user input in the CLI.
     * @return A Command based on the user input.
     * @throws InvalidCommand If the user enters either an missing command or an invalid command.
     */
    public static Command prase(String userInput) throws InvalidCommand {
        String command;
        String parameter = "";
        String[] splited = userInput.split("\\s+");
        if (splited.length < 2) {
            command = userInput;
        } else {
            command = splited[0];
            parameter = userInput.substring(command.length() + 1, userInput.length());
        }
        return Command.getCommand(command.toUpperCase(), parameter);
    }
}
