package duke.util;

import duke.task.TaskList;
import duke.command.*;
import duke.exception.CommandNotFoundException;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.tasks.Deadline;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;

public class Parser {
    public static Command parse(String line) throws DukeException {
        String[] input = line.strip().split(" ", 2);

        String command = input[0];
        boolean isValidArgument = input.length == 2;

        if (Constants.TASKS.contains(command) && !isValidArgument) {
            throw new InvalidArgumentException();
        }

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int taskId = Integer.parseInt(input[1]);
            return new MarkCommand(taskId);
        case "unmark":
            taskId = Integer.parseInt(input[1]);
            return new MarkCommand(taskId);
        case "find":
            String keyWord = input[1];
            return new FindCommand(keyWord);
        case "todo":
            String toDo = input[1].strip();
            return new AddCommand(new ToDo(toDo));
        case "event":
            String[] eventDetails = input[1].strip().split(" /at ", 2);
            return new AddCommand(new Event(eventDetails[0], eventDetails[1]));
        case "deadline":
            String[] deadlineDetails = input[1].strip().split(" /by ", 2);
            return new AddCommand(new Deadline(deadlineDetails[0], deadlineDetails[1]));
        case "delete":
            taskId = Integer.parseInt(input[1]);
            return new DeleteCommand(taskId);
        default:
            throw new CommandNotFoundException();
        }
    }
}
