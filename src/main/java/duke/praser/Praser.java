package duke.praser;

import duke.command.Command;
import duke.dukeexceptions.InvalidCommand;

public class Praser{
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
