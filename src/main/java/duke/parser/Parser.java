package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.DoAfterTask;
import duke.task.Event;
import duke.task.Todo;

public class Parser {
    /**
     *
     * @param fullCommand
     * @return
     * @throws DukeException
     */
    /** Interprets the input and determines the respective command*/
    public static Command parseCommand(String fullCommand) throws DukeException {
        assert fullCommand.isEmpty() == false : "duke.command.Command cannot be empty";
        String[] initial = fullCommand.split(" ", 2);
        switch (initial[0]) {

            case ("list"):
                return new ListCommand();
            case ("todo"):
                return new AddCommand(new Todo(initial[1]));
            case ("event"): {
                String[] second = initial[1].split("/at", 2);
                return new AddCommand(new Event(second[0], second[1]));
            }
            case ("deadline"): {
                String[] second = initial[1].split("/by", 2);
                return new AddCommand(new Deadline(second[0], (second[1])));
            }
            case ("doAfterTask"): {
                String[] second = initial[1].split("/after", 2);
                return new AddCommand(new DoAfterTask(second[0], (second[1])));
            }
            case ("markdone"):
                return new DoneCommand(Integer.parseInt(initial[1]));
            case ("unmark"):
                return new UndoneCommand(Integer.parseInt(initial[1]));
            case ("find"):
                return new FindCommand(initial[1].split(" "));
            case ("delete"):
                return new DeleteCommand(Integer.parseInt(initial[1]));
            case ("bye"):
                return new ExitCommand();


            default:
                throw new DukeException("Unknown command received.");
        }
    }
}