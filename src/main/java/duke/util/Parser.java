package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ReminderCommand;
import duke.command.SaveCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/**
 * Main logic to parse and translate user inputs.
 */
public class Parser {

    /**
     * Translates user inputs and performs the supposed actions.
     *
     * @param data      User input.
     * @param tasksList TasksList variable from Duke.
     * @param storage   Storage variable from Duke.
     * @return Response text to be printed by the UI.
     */
    public String parse(String data, TasksList tasksList, Storage storage) {
        try {
            String[] instruction = data.split(" ");

            if (instruction.length == 0 || !Constants.COMMANDS.contains(instruction[0])) {
                throw new InvalidCommandException();
            }

            //variables needed for switch case.
            Command command;

            switch(instruction[0]) {
            case "bye":
                command = new ByeCommand();
                break;

            case "list":
                command = new ListCommand();
                break;

            case "help":
                command = new HelpCommand();
                break;

            case "todo":
            case "event":
            case "deadline":
                command = new AddCommand(instruction);
                break;

            case "find":
                command = new FindCommand(instruction);
                break;

            case "mark":
                command = new MarkCommand(instruction);
                break;

            case "unmark":
                command = new UnmarkCommand(instruction);
                break;

            case "delete":
                command = new DeleteCommand(instruction);
                break;

            case "reminder":
                command = new ReminderCommand(instruction);
                break;

            case "save":
                command = new SaveCommand();
                break;

            default:
                throw new DukeException("Something is wrong!");
            }
            return command.execute(tasksList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
