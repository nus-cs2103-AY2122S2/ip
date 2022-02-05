package duke.system;

import duke.commands.Command;
import duke.commands.CommandClear;
import duke.commands.CommandDeadline;
import duke.commands.CommandDelete;
import duke.commands.CommandEvent;
import duke.commands.CommandExit;
import duke.commands.CommandFind;
import duke.commands.CommandHelp;
import duke.commands.CommandList;
import duke.commands.CommandMark;
import duke.commands.CommandToDo;
import duke.commands.CommandUnmark;
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
     * This method processes the user's input by splitting it at each
     * regex: "/" into a String array params.
     * If params is not empty, a valid input in assumed at this stage.
     * The first element of params will be the name of the command, which
     * will be compared against an enum of commands.
     * If the enum contains the command, the select method will be called on
     * params, and the ordinal of the command in the enum.
     * Else a DukeException will be thrown.
     *
     * @param fullCommand the unprocessed command input by the user
     * @return Command - returns a Command object
     * @throws DukeException - if input or command is invalid
     *
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

        return select(commandNum, params);
    }

    /**
     * This method processes the user's input by splitting it at each
     * regex: "/" into a String array params.
     * If params is not empty, a valid input in assumed at this stage.
     * The first element of params will be the name of the command, which
     * will be compared against an enum of commands.
     * If the enum contains the command, the select method will be called on
     * params, and the ordinal of the command in the enum.
     * Else a DukeException will be thrown.
     *
     * @param num the int used for the switch case statement
     * @param params the String array containing all the
     *               parameters of the command
     * @return Command the Command to be returned to the parse
     *                 method
     * @throws DukeException - If params size does not match the number
     *                         of required parameters of the command
     *
     */
    public static Command select(int num, String[] params) throws DukeException {
        switch(num) {
        case 0: // todo
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandToDo(params[1]);
        case 1: // event
            if (params.length != 4) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandEvent(params[1], params[2], params[3]);
        case 2: // deadline
            if (params.length != 4) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandDeadline(params[1], params[2], params[3]);
        case 3: // list
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandList();
        case 4: // mark
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandMark(params[1]);
        case 5: // unmark
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandUnmark(params[1]);
        case 6: // delete
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandDelete(params[1]);
        case 7: // exit
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandExit();
        case 8: // help
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandHelp();
        case 9: // find
            if (params.length != 2) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandFind(params[1]);
        case 10: // find
            if (params.length != 1) {
                throw new DukeException("Invalid command! "
                        + "Input /chelp to see the full list of commands!");
            }
            return new CommandClear();
        default: // invalid
            throw new DukeException("Invalid command! "
                    + "Input /chelp to see the full list of commands!");
        }
    }
}
