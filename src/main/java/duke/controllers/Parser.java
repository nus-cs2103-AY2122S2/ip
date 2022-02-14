package duke.controllers;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.commands.*;


public class Parser {

    public static Command parseCommand(String cmd) throws DukeException{
        String[] cmdVar = cmd.split(" ", 2);
        switch (cmdVar[0]) {
        case "list":
            return new ListCmd();
        case "mark":
            return new MarkCmd(Integer.parseInt(cmdVar[1]));
        case "unmark":
            return new UnmarkCmd(Integer.parseInt(cmdVar[1]));
        case "find":
            return new FindCmd(cmdVar[1]);
        case "todo":
            return new CreateTodoCmd(cmdVar);
        case "deadline":
            return new CreateDeadlineCmd(cmdVar);
        case "event":
            return new CreateEventCmd(cmdVar);
        case "delete":
            return new DeleteCmd(Integer.parseInt(cmdVar[1]));
        case "bye":
            return new ByeCommand();

        default:
            //TODO: make it a exception
            throw new DukeException("I'm not sure what that means :((");
        }

    }

    public static String[] parseHashtable(String cmd) {
        return cmd.split(" " );
    }

}
