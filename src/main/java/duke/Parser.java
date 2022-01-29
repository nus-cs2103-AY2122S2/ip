package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.ToggleCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {

    public static int parseInt(String strToIntValue) throws DukeException {
        try {
            return Integer.parseInt(strToIntValue);
        } catch (Exception e) {
            throw new DukeException("Please provide the item NUMBER to remove.");
        }
    }

    public static Command parseCommand(String fullCommand) {
        Command cmd;
        String[] input = fullCommand.split(" ", 2);

        switch (input[0].toLowerCase()) {
        case "exit":
            cmd = new ExitCommand();
            break;
        case "list":
            cmd = new ListCommand();
            break;
        case "mark":
        case "unmark":
            cmd = new ToggleCommand(input[0], input[1]);
            break;
        case "todo":
        case "event":
        case "deadline":
            cmd = new AddCommand(input[0], input[1]);
            break;
        case "remove":
        case "delete":
            cmd = new DeleteCommand(input[1]);
            break;
        default:
            cmd = new InvalidCommand();
            break;
        }
        return cmd;
    }

    public static Task parseStringToTask(String entry) throws DukeException {
        String[] input = entry.split(" \\| ");
        Task newTask;
        switch (input[0]) {
        case "T":
            newTask = new ToDo(input[2],input[1]);
            break;
        case "D":
            newTask = new Deadline(input[2], input[3], input[1]);
            break;
        case "E":
            newTask = new Event(input[2], input[3], input[1]);
            break;
        default:
            throw new DukeException("Invalid keyword from source file.");
        }

        return newTask;
    }
}