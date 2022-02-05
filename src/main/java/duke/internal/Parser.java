package duke.internal;

import java.util.ArrayList;

import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Represents a manager that handles recognition of user input.
 */
public class Parser {

    protected ArrayList<Command> commands;

    /**
     * Creates an instance of a Parser and initializes its internal command list.
     */
    public Parser() {
        commands = new ArrayList<>();
    }

    /**
     * Adds a new command to the list of recognized commands.
     *
     * @param command the command to be added.
     */
    public void addCommand(Command command) {
        commands.add(command);
    }

    /**
     * Takes in a user input and attempts to link it to a specific
     * command.
     *
     * @param input a String that represents the input given by the user.
     * @returna command that is recognized and created by the parser according
     *          to the input.
     * @throws DukeException when no command can be matched to the user input.
     */
    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ");
        Command recognizedCommand = null;
        for (Command c : commands) {
            if (c.checkIdentifier(tokens[0])) {
                recognizedCommand = c;
                break;
            }
        }
        if (recognizedCommand == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        } else {
            recognizedCommand.handleParam(tokens);
            return recognizedCommand;
        }
    }
}
