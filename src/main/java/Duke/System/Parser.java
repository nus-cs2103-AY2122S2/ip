package Duke.System;

import Duke.Commands.Command;
import Duke.Commands.CommandDeadline;
import Duke.Commands.CommandDelete;
import Duke.Commands.CommandEvent;
import Duke.Commands.CommandExit;
import Duke.Commands.CommandFind;
import Duke.Commands.CommandHelp;
import Duke.Commands.CommandList;
import Duke.Commands.CommandMark;
import Duke.Commands.CommandToDo;
import Duke.Commands.CommandUnmark;
import Duke.DukeException.DukeException;
import Duke.Enums.Commands;

public class Parser {
    public Parser() {}

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
        default: // invalid
            throw new DukeException("Invalid command! "
                    + "Input /chelp to see the full list of commands!");
        }
    }
}
