package duke.system;

import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;
import duke.enums.Commands;
import duke.exceptions.DukeException;

/**
 * The Parser class will allow Duke to
 * understand and act upon the user's input.
 *
 * @author  Melvin Chan Zijun
 */
public class Parser {
    /**
     * Processes the user's input by splitting it at each
     * regex: "/" into a multiple parameters.
     * If there is at least one parameter, a valid input in assumed at this stage.
     * The first parameter will always be the command, which
     * will be compared against an enum of commands.
     * If the enum contains the command, the selectCommand method will be called
     * with the ordinal of the command in the enum to determine the
     * command to be returned.
     * Else a DukeException will be thrown.
     *
     * @param fullCommand the unprocessed full command input
     * @return Command the command to be executed
     * @throws DukeException if input or command is invalid
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] params = fullCommand.split("/");

        if (params.length == 0) {
            throw new DukeException("Invalid input!");
        }

        String command = params[0].toUpperCase();

        boolean containsCommand = false;
        int commandNum = -1;
        Commands[] commands = Commands.values();
        for (Commands c : commands) {
            if (c.toString().equals(command)) {
                commandNum = c.ordinal();
                containsCommand = true;
                break;
            }
        }

        if (!containsCommand) {
            throw new DukeException("Invalid command!");
        }


        return selectCommand(commandNum, params);
    }

    /**
     * Handles the creation of new commands if given valid inputs
     *
     * @param num the ordinal num of the command in the Commands enum
     * @param params the String array containing all the
     *               parameters of the command
     * @return Command the Command to be returned
     * @throws DukeException if params size does not match the number
     *                         of required parameters of the command
     */
    public static Command selectCommand(int num, String[] params) throws DukeException {
        switch(num) {
        case 0: // todo
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new ToDoCommand(params[1]);
        case 1: // event
            if (params.length != 4) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new EventCommand(params[1], params[2], params[3]);
        case 2: // deadline
            if (params.length != 4) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new DeadlineCommand(params[1], params[2], params[3]);
        case 3: // list
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new ListCommand();
        case 4: // mark
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new MarkCommand(params[1]);
        case 5: // unmark
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new UnmarkCommand(params[1]);
        case 6: // delete
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new DeleteCommand(params[1]);
        case 7: // exit
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new ExitCommand();
        case 8: // help
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new HelpCommand();
        case 9: // find
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new FindCommand(params[1]);
        case 10: // find
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new ClearCommand();
        default: // invalid
            throw new DukeException("Invalid command! "
                    + "Input /chelp to see the full list of commands!");
        }
    }
}
